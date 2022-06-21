<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Notification extends Model
{
    use HasFactory;
    protected $table = "notifications";      ### Rename table name if you want , it depends on your local DataBase ###
    protected $fillable=['user_id','source','content','time'];
    public $timestamps=false;
}
