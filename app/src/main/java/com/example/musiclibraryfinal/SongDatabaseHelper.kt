package com.example.musiclibraryfinal

import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class SongDatabaseHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TITLE TEXT, $COLUMN_ARTIST TEXT, $COLUMN_GENRE TEXT, $COLUMN_YEAR INTEGER")
        db?.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAllItems(): List<Song> {
        val playlist = mutableListOf<Song>()
        val db = this.readableDatabase

        val cursor: Cursor = db.query(
            TABLE_NAME, arrayOf(COLUMN_ID, COLUMN_TITLE, COLUMN_ARTIST, COLUMN_GENRE, COLUMN_YEAR),
            null, null, null, null, null)
        if(cursor.moveToFirst()){
            do {
                val idIndex = cursor.getColumnIndex(COLUMN_ID)
                val ID = cursor.getInt(idIndex)
                if(ID != -1){
                    val songTitleIndex = cursor.getColumnIndex(COLUMN_TITLE)
                    val songTitle = cursor.getString(songTitleIndex)

                    val songArtistIndex = cursor.getColumnIndex(COLUMN_ARTIST)
                    val songArtist = cursor.getString(songArtistIndex)

                    val songGenreIndex = cursor.getColumnIndex(COLUMN_GENRE)
                    val songGenre = cursor.getString(songGenreIndex)

                    val songYearIndex = cursor.getColumnIndex(COLUMN_YEAR)
                    val songYear = cursor.getInt(songYearIndex)

                    playlist.add(Song(songTitle, songArtist, songGenre, songYear))
                }
            } while(cursor.moveToNext())
        }
        cursor.close()
        return playlist
    }

    companion object {
        private const val DATABASE_NAME = "songs.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "songs"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_ARTIST = "artist"
        private const val COLUMN_GENRE = "genre"
        private const val COLUMN_YEAR = "year"

        @Volatile
        private var INSTANCE: SongDatabaseHelper? = null

        fun getInstance(context: Context): SongDatabaseHelper {
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: SongDatabaseHelper(context.applicationContext).also{INSTANCE = it}
            }
        }
    }


}