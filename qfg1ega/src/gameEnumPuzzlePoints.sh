;;; Sierra Script 1.0 - (do not remove this comment)

; ///////////////////////////////////////////////////////
; Puzzle Point Event Flags (142 flags allocated; 6 flags unused: 609, 692, 710, 712, 714, 716)
; ///////////////////////////////////////////////////////
(enum 601
	
	POINTS_KILLBEAR 	;601)             ; -25 points
	POINTS_KILLKOBOLD 	;602)           ; 10 points (Fighter or Magic User only)
	POINTS_SMASHSPIREA 	;603)          ; -10 points
	POINTS_KILLFOX 		;604)              ; -10 points
	POINTS_GOTDRUNK 	;605)             ; -5 points
; events 606 to 644 are character specific points, listed at bottom.

	POINTS_GETROBBED 	;606)            ; -10 points (Thief only)

; FIGHTER ONLY POINTS
	POINTS_FIGHTWEAPONMASTER ;607)        ; 3 points (Fighter only)
	POINTS_DEFEATWEAPONMASTER ;608)       ; 10 points (Fighter only)
; no event 609
	POINTS_UNUSED_609
	POINTS_BUYCHAINMAIL ;610)         ; 3 points (Fighter only)
	POINTS_KILLTROLL 	;611)            ; 4 points (Fighter only)
	POINTS_KILLCHEETAUR ;612)         ; 4 points (Fighter Only)
	POINTS_KILLSAURUSREX ;613)        ; 4 points (Fighter Only)
;remaining Fighter only Points are at the bottom:

; MAGE ONLY POINTS
	 POINTS_LEARNCALM ;614)            ; 4 points (Mage only)
	 POINTS_SLEEPERANA ;615)           ; 5 points (Mage only)
	 POINTS_PLAYWIZARDGAME ;616)       ; 5 points (Mage only)
	 POINTS_WINWIZARDGAME ;617)        ; 12 points (Mage only)
	 POINTS_LEARNDETECTMAGIC ;618)         ; 4 points (Mage only)
	 POINTS_LEARNTRIGGER ;619)         ; 4 points (Mage only)
	 POINTS_LEARNFETCH ;620)           ; 2 points (Mage only)
	 POINTS_LEARNOPEN ;621)            ; 2 points (Mage only)
	 POINTS_LEARNFLAMEDART ;622)       ; 2 points (Mage only)
	; THIEF ONLY POINTS
	 POINTS_ENTERLOLHOUSE ;623)        ; 5 points (Thief Only)
	 POINTS_SEARCHLOLDESK ;624)        ; 1 point (Thief Only)
	 POINTS_SEARCHLOLCOUCH ;625)       ; 1 point (Thief Only)
	 POINTS_SEARCHLOLPURSE ;626)       ; 1 point (Thief Only)
	 POINTS_SEARCHLOLBASKET ;627)      ; 1 point (Thief Only)
	 POINTS_TAKECANDLESTICKS ;628)         ; 1 point (Thief Only)
	 POINTS_PETLOLCAT ;629)            ; 3 points (Thief Only)
	 POINTS_ENTERSHERIFFHOUSE ;630)        ; 5 points (Thief only)
	 POINTS_SEARCHSHERIFFDRAWER ;631)      ; 1 point (Thief only)
	 POINTS_MOVEPAINTING ;632)         ; 1 point (Thief only)
	 POINTS_CRACKSAFE ;633)            ; 1 point (Thief only)
	 POINTS_TAKESAFEMONEY ;634)       ; 1 point (Thier only)
	 POINTS_TAKEVASE ;635)             ; 1 point (Thief only)
	 POINTS_TAKECANDELABRA ;636)       ; 1 point (Thief only)
	 POINTS_TAKEMUSICBOX ;637)         ; 1 point (Thief only]
	 POINTS_ENTERTHIEVESGUILD ;638)        ; 5 points (Thief Only)
	 POINTS_BUYTHIEFLICENSE ;639)      ; 3 points (Thief Only)
	 POINTS_BUYTHIEFTOOLKIT ;640)      ; 3 points (Thief Only)
	 POINTS_FENCEGOODS ;641)           ; 3 points (Thief only)
	 POINTS_SHOWTHIEFSIGN ;642)        ; 3 points (Thief only)
	 POINTS_PLAYDAGNABIT ;643)         ; 3 points (Thief Only)
	 POINTS_WINDAGNABITSMALL ;644)         ; 5 points (Thief Only)
	
	; All class points
	 POINTS_EATERANAFRUIT ;645)        ; 2 points
	 POINTS_CALMBEAR ;646)             ; 5 points
	 POINTS_FREEBEAR ;647)             ; 25 points
	 POINTS_ENTERKOBOLDCAVE ;648)      ; 2 points
	 POINTS_TAKEKOBOLDKEY ;649)        ; 7 points
	 POINTS_TAKEKOBOLDMONEY ;650)      ; 5 points
	 POINTS_GETSEED ;651)              ; 8 points
	 POINTS_ENTERBABAYAGAHUT ;652)     ; 2 points
	 POINTS_TURNBABAYAGAINTOFROG ;653)     ; 50 points
	 POINTS_GIVEMANDRAKEROOT ;654)     ; 3 points
	 POINTS_MAKEDEALWITHSKULL ;655)        ; 2 points
	 POINTS_GIVEGEM ;656)              ; 10 points
	 POINTS_HUTSIT ;657)               ; 7 points
	 POINTS_ENTERERASMUSCASTLE ;658)       ; 3 points
	 POINTS_TALKTOERASMUS ;659)        ; 1 point
	 POINTS_ENTERCASTLECOURTYARD ;660)         ; 1 point
	 POINTS_TALKTOWEAPONMASTER ;661)       ; 1 point
	 POINTS_WORKINSTABLES ;662)        ; 5 points
	 POINTS_MEETBARON ;663)            ; 10 points
	 POINTS_TALKTOBARON ;664)          ; 3 points
	 POINTS_TALKTOFARMER ;665)         ; 1 points
	 POINTS_TALKTOFARMERABOUTLEADER ;666)     ; 3 points
	 POINTS_GETGOLDRING ;667)          ; 3 points
	 POINTS_TALKTOHEALER ;668)         ; 2 points
	 POINTS_RETURNRING ;669)           ; 10 points
	 POINTS_SELLMUSHROOM ;670)         ; 1 point
	 POINTS_SELLCHEETAURCLAW ;671)     ; 2 points
	 POINTS_SELLTROLLBEARD ;672)       ; 2 points
	 POINTS_SELLFLOWERS ;673)          ; 1 points
	 POINTS_GIVEFLYINGWATER ;674)      ; 2 points
	 POINTS_GIVEMAGICACORN ;675)       ; 5 points
	 POINTS_GIVEGREENFUR ;676)         ; 2 points
	 POINTS_GIVEFAIRYDUST ;677)        ; 2 points
	 POINTS_GETDISPELPOTION ;678)      ; 7 points
	 POINTS_GETGLOWINGGEM ;679)        ; 8 points
	 POINTS_TALKTOMEEP ;680)           ; 1 point
	 POINTS_GETGREENFUR ;681)          ; 5 points
	 POINTS_USEUNDEADUNGUENT ;682)     ; 2 points
	 POINTS_GETMANDRAKEROOT ;683)      ; 6 points
	 POINTS_LEAVETOWNFIRSTTIME ;684)       ; 1 point
	 POINTS_RECEIVEBADADVICEFROMBRUNO 	;685)        ; 2 points
	 POINTS_FREEFOX 					;686)              ; 10 points
	 POINTS_TALKTOFAIRIES 				;687)            ; 1 point
	 POINTS_DANCEWITHFAIRIES 			;688)         ; 3 points
	 POINTS_GETFAIRYDUST 				;689)         ; 8 points
	 POINTS_PICKMUSHROOMS 				;690)        ; 3 points
	 POINTS_OVERHEARBRUNO 				;691)        ; 12 points
	 POINTS_UNUSED_692
	; no event 692
	 POINTS_AGREETOHELPDRYAD 			;693)     ; 1 point
	 POINTS_GIVESEED 					;694)             ; 7 points
	 POINTS_GETACORN 					;695)             ; 1 point
	 POINTS_EATACORN 					;696)             ; -5 points
	 POINTS_KNOCKONHERMITDOOR 			;697)        ; 1 point
	 POINTS_GETFLYINGWATER 				;698)       ; 3 points
	 POINTS_MEETHERMIT 					;699)           ; 5 points
	 POINTS_TALKTOHERMIT 				;700)         ; 2 points
	 POINTS_FINDSECRETENTRANCE 			;701)       ; 10 points
	 POINTS_GIVECAVEPASSWORD 			;702)         ; 5 points
	 POINTS_VISITLAKE 					;703)            ; 1 point
	 POINTS_ENTERSECRETENTRANCE 		;704)      ; 2 points
	 POINTS_ENTERBRIGANDFORTRESS 		;705)     ; 8 points
	 POINTS_ENTERBRIGANDDININGROOM 		;706)       ; 8 points
	 POINTS_ENTERBRIGANDJESTERROOM 		;707)       ; 8 points
	 POINTS_TALKTOJESTER 				;708)         ; 2 points
	 POINTS_TALKTOJESTERABOUTELSA 		;709)        ; 8 points
	
	POINTS_UNUSED_710
	; no event 710
	 POINTS_ENTERBRIGANDLEADERROOM 		;711)       ; 12 points
	
	POINTS_UNUSED_712
	; no event 712
	 POINTS_DISPELBRIGANDLEADER 		;713)          ; 35 points
	POINTS_UNUSED_714
	; no event 714
	 POINTS_TAKEMAGICMIRROR 			;715)      ; 10 points
	POINTS_UNUSED_716
	; no event 716
	 POINTS_BEGINGAME 					;717)            ; 1 point
	 POINTS_TALKTOSHERIFF 				;718)        ; 1 point
	 POINTS_TALKTOSHAMEEN 				;719)        ; 1 point
	 POINTS_TALKTOABDULA 				;720)         ; 5 points
	 POINTS_EATKATTAFOOD 				;721)         ; 1 point
	 POINTS_RENTROOM 					;722)             ; 1 point
	 POINTS_READLOGBOOK 				;723)          ; 4 points
	 POINTS_SIGNLOGBOOK 				;724)          ; 1 point
	 POINTS_TALKTOGUILDMASTER 			;725)        ; 1 point
	 POINTS_READNOTICEBOARD 			;726)      ; 6 points
	 POINTS_TALKTOZARA 					;727)           ; 1 point
	 POINTS_BUYAPPLES 					;728)            ; 3 points
	 POINTS_TALKTOCENTAURGIRL 			;729)        ; 1 point
	 POINTS_TALKTOSHOPKEEPER 			;730)         ; 1 point
	 POINTS_PICKUPNOTE 					;731)           ; 2 points
	 POINTS_TALKTOBEGGAR 				;732)         ; 1 point
	 POINTS_GIVEALMS 					;733)             ; 1 point
	 POINTS_WINGAME 					;734)              ; 25 points
	 POINTS_TALKTOGATEKEEPER 			;735)         ; 5 points
	 POINTS_GIVEABDULAFOOD 				;736)       ; 2 points
	
	;remaining Fighter Only points.
	 POINTS_KILLMINOTAUR 				;737)         ; 5 points (Fighter only)
	 POINTS_KILLMANTRAY 				;738)          ; 2 points (Fighter only)
	 POINTS_KILLOGRE 					;739)             ; 2 points (Fighter only)
	 POINTS_KILLBRIGAND 				;740)          ; 1 point (Fighter Only)
	 POINTS_KILLSAURUS 					;741)           ; 1 point (Figher only)
	 POINTS_KILLGOBLIN 					;742)           ; 1 point (Fighter Only)
)	
