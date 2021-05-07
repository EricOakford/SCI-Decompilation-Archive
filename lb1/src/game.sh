;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here
(include system.sh) (include sci2.sh)

(enum
	iNecklace		;0
	iMonocle		;1
	iLantern		;2
	iOilcan			;3
	iRollingPin		;4
	iSkeletonKey	;5
	iPoker			;6
	iCrowbar		;7
	iCigarButt		;8
	iBrokenRecord	;9
	iNotebook		;10
	iCrackers		;11
	iSoupBone		;12
	iValveHandle	;13
	iBullet			;14
	iDerringer		;15
	iMatches		;16
	iCarrot			;17
	iBrassKey		;18
	iDiary			;19
	iCrank			;20
	iCane			;21
	iPouch			;22
	iHandkerchief	;23
)

;Memory sizes
(define InvSize 2048)

;Regions
(define ETHEL_DRUNK 202)
(define OUTSIDE 208)
(define DEAD_WILBUR 256)
(define LILLREAD 257)
(define WALKABOUT 381)
(define BARKING 400)
(define SCUFFING 401)
(define RUDYEAT 403)
(define GATOR 405)
(define CLOCK 406)

;Game acts
(enum
	actI
	actII
	actIII
	actIV
	actV
	actVI
	actVII
)

;Talkers
(define talkCELIE 2)
(define talkFIFI 5)
(define talkLILLIAN 6)
(define talkCLARENCE 7)
(define talkWILBUR 8)
(define talkRUDY 9)
(define talkJEEVES 11)
(define talkLAURA 12)
(define talkDADDY 26)

;Event flags
(define fSawShadowyFigure 0)
(define fReadDiary 3)
(define fExaminedHandkerchief 6)
(define fExaminedRollingPin 7)
(define fExaminedPoker 8)
(define fExaminedRecord 9)
(define fExaminedDiary 12)
(define fExaminedCigar 13)
(define fExaminedMagazine 17)
(define SAW_FIFI_JEEVES_TOGETHER 22)
(define fFlag36	36)
(define fDaddyTip1 41)
(define fDaddyTip2 42)
(define fDaddyTip3 43)
(define fSkippedIntro 50)
(define fFlag51 51)

;Murdered guests
(define deadGERTRUDE	$0001)
(define deadGLORIA		$0004)

;howFast values
(enum
	slow
	medium
	fast
)