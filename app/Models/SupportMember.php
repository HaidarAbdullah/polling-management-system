<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class SupportMember extends Model
{
    use HasFactory;
    protected $table = "supportmembers";      // Rename table name if you want , it depends on your local DataBase
    protected $fillable=['name','email','password'];
    public $timestamps=false;
}
