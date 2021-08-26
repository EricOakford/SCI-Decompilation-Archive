;;; Sierra Script 1.0 - (do not remove this comment)

; Puzzle Point Event Flags (142 flags allocated; 6 flags unused: 609, 692, 710, 712, 714, 716)
(enum 601
	f420BeatBear	 	;601)             ; -25 points
	f15BeatKobold 	;602)           ; 10 points (Fighter or Magic User only)
	f10KillFlower 	;603)          ; -10 points
	f67KillFox 		;604)              ; -10 points
	f331Drunk 	;605)             ; -5 points
; events 606 to 644 are character specific points, listed at bottom.

	f334Robbed 	;606)            ; -10 points (Thief only)

; FIGHTER ONLY POINTS
	f39FightMaster ;607)        ; 3 points (Fighter only)
	f39BeatMaster ;608)       ; 10 points (Fighter only)
; no event 609
	fUnused609
	f322BuyChainmail ;610)         ; 3 points (Fighter only)
	f450BeatTroll 	;611)            ; 4 points (Fighter only)
	f440BeatCheetaur	;612)         ; 4 points (Fighter Only)
	f460BeatDragon	;613)        ; 4 points (Fighter Only)
;remaining Fighter only Points are at the bottom:

; MAGE ONLY POINTS
	f10LearnCalm ;614)            ; 4 points (Mage only)
	f10SleepInMeadow ;615)           ; 5 points (Mage only)
	f32PlayMaze ;616)       ; 5 points (Mage only)
	f32WinMaze ;617)        ; 12 points (Mage only)
	f60LearnDetect ;618)         ; 4 points (Mage only)
	POINTS_LEARNTRIGGER ;619)         ; 4 points (Mage only)
	f314LearnFetch ;620)           ; 2 points (Mage only)
	f314LearnOpen ;621)            ; 2 points (Mage only)
	f314LearnFlameDart ;622)       ; 2 points (Mage only)
	; THIEF ONLY POINTS
	f313EnterLOLHouse ;623)        ; 5 points (Thief Only)
	f313SearchDesk ;624)        ; 1 point (Thief Only)
	f313SearchCouch ;625)       ; 1 point (Thief Only)
	f313SearchPurse ;626)       ; 1 point (Thief Only)
	f313SearchBasket ;627)      ; 1 point (Thief Only)
	f313StealCandles ;628)         ; 1 point (Thief Only)
	f313PetCat ;629)            ; 3 points (Thief Only)
	POINTS_ENTERSHERIFFHOUSE ;630)        ; 5 points (Thief only)
	POINTS_SEARCHSHERIFFDRAWER ;631)      ; 1 point (Thief only)
	POINTS_MOVEPAINTING ;632)         ; 1 point (Thief only)
	POINTS_CRACKSAFE ;633)            ; 1 point (Thief only)
	POINTS_TAKESAFEMONEY ;634)       ; 1 point (Thier only)
	POINTS_TAKEVASE ;635)             ; 1 point (Thief only)
	POINTS_TAKECANDELABRA ;636)       ; 1 point (Thief only)
	POINTS_TAKEMUSICBOX ;637)         ; 1 point (Thief only]
	f332EnterGuild ;638)        ; 5 points (Thief Only)
	f332BuyLicense ;639)      ; 3 points (Thief Only)
	f332BuyToolkit ;640)      ; 3 points (Thief Only)
	f332FenceGoods ;641)           ; 3 points (Thief only)
	f334GiveSign ;642)        ; 3 points (Thief only)
	f340PlayDagNabIt ;643)         ; 3 points (Thief Only)
	f340WinBigBet ;644)         ; 5 points (Thief Only)
	
	; All class points
	f10EatMeadowFruit ;645)        ; 2 points
	f14CalmBear ;646)             ; 5 points
	f14FreeBear ;647)             ; 25 points
	f15EnterCave ;648)      ; 2 points
	f15GetKey ;649)        ; 7 points
	f15GetTreasure ;650)      ; 5 points
	f16GetSeed ;651)              ; 8 points
	f21EnterHut ;652)     ; 2 points
	f21BabaFrog ;653)     ; 50 points
	f21GiveRoot ;654)     ; 3 points
	f22MakeTheDeal ;655)        ; 2 points
	f22GiveGem ;656)              ; 10 points
	f22HutSit ;657)               ; 7 points
	f30EnterTower ;658)       ; 3 points
	f31TalkToErasmus ;659)        ; 1 point
	f39EnterCourtyard ;660)         ; 1 point
	f39TalkToMaster ;661)       ; 1 point
	f40WorkInStable ;662)        ; 5 points
	f141MeetBaron ;663)            ; 10 points
	f141TalkToBaron ;664)          ; 3 points
	f53TalkToHeinrich ;665)         ; 1 points
	f53AskAboutLeader ;666)     ; 3 points
	f54GetRing ;667)          ; 3 points
	f55TalkToHealer ;668)         ; 2 points
	f55ReturnRing ;669)           ; 10 points
	f55SellMushroom ;670)         ; 1 point
	f55SellClaws ;671)     ; 2 points
	f55SellBeard ;672)       ; 2 points
	f55SellFlowers ;673)          ; 1 points
	f55GiveWater ;674)      ; 2 points
	f55GiveAcorn ;675)       ; 5 points
	f55GiveFur ;676)         ; 2 points
	f55GiveDust ;677)        ; 2 points
	f55GetDispel ;678)      ; 7 points
	f58GetGem ;679)        ; 8 points
	f60TalkToMeep ;680)           ; 1 point
	f60GetFur ;681)          ; 5 points
	f64UseGhostOil ;682)     ; 2 points
	f64GetRoot ;683)      ; 6 points
	f65LeaveTown ;684)       ; 1 point
	f65GotAdviceFromBruno 	;685)        ; 2 points
	f67SaveFox 					;686)              ; 10 points
	f70TalkToFairies 				;687)            ; 1 point
	f70DanceWithFairies 			;688)         ; 3 points
	f70GetDust 				;689)         ; 8 points
	f70GetMushrooms 				;690)        ; 3 points
	f73OverhearBruno 				;691)        ; 12 points
	fUnused692
	; no event 692
	f76AgreeToHelp 			;693)     ; 1 point
	f76GiveSeed 					;694)             ; 7 points
	f76GetAcorn 					;695)             ; 1 point
	fEatAcorn 					;696)             ; -5 points
	f82KnockOnDoor 			;697)        ; 1 point
	f82GetWater 				;698)       ; 3 points
	POINTS_MEETHERMIT 					;699)           ; 5 points
	POINTS_TALKTOHERMIT 				;700)         ; 2 points
	POINTS_FINDSECRETENTRANCE 			;701)       ; 10 points
	POINTS_GIVECAVEPASSWORD 			;702)         ; 5 points
	POINTS_VISITLAKE 					;703)            ; 1 point
	POINTS_ENTERSECRETENTRANCE 		;704)      ; 2 points
	f94EnterFortress 		;705)     ; 8 points
	f95EnterDiningRoom 		;706)       ; 8 points
	f96EnterJesterRoom 		;707)       ; 8 points
	f96TalkToJester 				;708)         ; 2 points
	f96AskAboutElsa 		;709)        ; 8 points
	fUnused710							;710
	f97EnterLeaderRoom 		;711)       ; 12 points
	
	fUnused712					; no event 712
	f172DispelLeader 		;713)          ; 35 points
	fUnused714					; no event 714
	POINTS_TAKEMAGICMIRROR 			;715)      ; 10 points
	fUnused716						;716
	f300EnterTown 						;717)            ; 1 point
	f300TalkToSheriff 					;718)        ; 1 point
	f301TalkToShameen				;719)        ; 1 point
	f301TalkToAbdulla	 				;720)         ; 5 points
	f301OrderMeal 						;721)         ; 1 point
	f301RentRoom					;722)             ; 1 point
	f311ReadBook 				;723)          ; 4 points
	f311SignBook 				;724)          ; 1 point
	f311TalkToWolfgang 			;725)        ; 1 point
	f311ReadBoard		 			;726)      ; 6 points
	f314TalkToZara 					;727)           ; 1 point
	f320BuyApples 					;728)            ; 3 points
	f320TalkToHilde 			;729)        ; 1 point
	f322TalkToKaspar 			;730)         ; 1 point
	f331GetNote 					;731)           ; 2 points
	f333TalkToBeggar				;732)         ; 1 point
	f333GiveAlms					;733)             ; 1 point
	f600EndGame 						;734)              ; 25 points
	f37TalkToKarl 			;735)         ; 5 points
	f301GaveMerchantFood 				;736)       ; 2 points
	
	;remaining Fighter Only points.
	f425BeatMinotaur 					;737)         ; 5 points (Fighter only)
	f435BeatMantray 				;738)          ; 2 points (Fighter only)
	f455BeatOgre 					;739)             ; 2 points (Fighter only)
	f465BeatBrigand				;740)          ; 1 point (Fighter Only)
	f430BeatSaurus 					;741)           ; 1 point (Figher only)
	f445BeatGoblin 					;742)           ; 1 point (Fighter Only)
)