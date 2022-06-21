<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class SupervisingInvitation extends Model
{
    use HasFactory;
    protected $table ="supervisinginvitations";      ### Rename table name if you want , it depends on your local DataBase ###
    protected $fillable=['room_id','creator_id','user_id','confirmation'];
    public $timestamps=false;
}
