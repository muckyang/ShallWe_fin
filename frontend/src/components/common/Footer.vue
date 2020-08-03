<template>
  <div>
    <div class="sub-footer">
        <div class="footer-blank"></div>
        <div d-inline>
            <a class="insta-a" href="#"><i class="fab fa-instagram insta"></i></a>
            <!-- <p class="text-white mt-3">Team. 욕망의 항아리</p> -->
        </div>
        <h6 class="team">Created by POT OF AMBITION</h6>
        <div class="d-flex flex-column">
            <div class="d-flex flex-row justify-content-center mt-3">
                <div class="mr-3">
                    <h6 class="text-white">Back end</h6>
                    <h6 class="text-white">Kim</h6>
                    <h6 class="text-white">Kim</h6>
                    <h6 class="text-white">Song</h6>
                    <!-- <div class="verticalLine">a</div> -->
                </div>
                <!-- <h1 class="my-board text-white">|</h1> -->
                <div class="right ml-3">
                    <h6 class="text-white">Front end</h6>
                    <h6 class="text-white">Cha</h6>
                    <h6 class="text-white">Lim</h6>
                    <h6 class="text-white">Lee</h6>
                </div>
                <!-- <hr class="vertical"> -->
                
            </div>
            <!-- <div class="end mt-2 ">
                <h6 class="copyright mb-0">
                    <i class="far fa-copyright"></i> 
                    Copyright 2020. All rights reserved.</h6>
            </div> -->
        </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'Footer',
}
</script>

<style>
  
    .sub-footer {
        height: 270px;
        background-color: #fec9c9;
    }
    .sub-footer-p {
        color: white;
    }
    .insta {
        color: white;
        font-size: 50px;
        padding-left:5px;
    }
    .footer-blank {
        height: 30px;
    }
    .my-board {
        font-stretch: extra-condensed;
        height: 50px;
    }
    .footer {
        background-color: #fec9c9;
    }
    div.verticalLine{
        /* width: 1px;
        height:40%; */
        /* background-color: #676d74; */ 
        border-color: black;
        border-left-color: white;
    }
    .right{
        border-left: 1.5px solid white;
        padding-left: 25px;

    }
    .team{
        color: white;
        margin-top: 18px;
    }
    .copyright{
        color: white;
        margin-top:5px;
        font-size: small;
        padding-left: 10px;
    }
    .end{
        background-color:#fec9c9;
    }
</style>