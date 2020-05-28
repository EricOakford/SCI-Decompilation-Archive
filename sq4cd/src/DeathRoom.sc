;;; Sierra Script 1.0 - (do not remove this comment)
(script# EGODEAD) ;900
(include game.sh) (include "900.shm")
(use Main)
(use SQRoom)
(use Sq4Dialog)
(use Print)
(use Sq4Narrator)
(use Sq4Feature)
(use ForCount)
(use DPath)
(use Motion)
(use System)

(public
	rm900 0
)

(local
	[len 4]
)
(procedure (Measure theString)
	(TextSize @len theString 0 122)
	(return (/ (- 180 (- [len 2] [len 0])) 2))
)

(procedure (DisplayDeath strg &tmp theY [str 100])
	(= theY (Measure strg))
	(Display strg
		p_at 168 theY
		p_width 122
		p_font SYSFONT
		p_mode teJustLeft
	)
)

(instance rm900 of SQRoom
	(properties
		picture EGODEAD
		style FADEOUT
	)
	
	(method (init &tmp case [str 1000])
		(HandsOff)
		(theGame setCursor: waitCursor TRUE)
		(globalSound stop:)
		(music prevSignal: 0)
		(super init:)
		(= case
			(switch deathMessage
				(deathGENERIC C_DIE_GENERIC)
				(deathSMOKING C_DIE_SMOKING)
				(deathSHOPLIFTING C_DIE_SHOPLIFTING)
				(deathDILLYDALLY C_DIE_DILLYDALLY)
				(deathBLOWNUP C_DIE_BLOWNUP)
				(deathFORMATTED C_DIE_FORMATTED)
				(deathSTIFF C_DIE_STIFF)
				(deathFORCEFIELD C_DIE_FORCEFIELD)
				(deathFALLSTREETS C_DIE_FALL_STREETS)
				(deathSTANDTHERE C_DIE_STANDTHERE)
				(deathKNEWCOMING C_DIE_KNEWCOMING)
				(deathVOHAULWINS C_DIE_VOHAUL_WINS)
				(deathZEROIQ C_DIE_ZEROIQ)
				(deathSLIME C_DIE_SLIMED)
				(deathSKATELUMP C_DIE_SKATE_LUMP)
				(deathSKATEORAMA C_DIE_SKATEORAMA)
				(deathORTEGA C_DIE_ORTEGA)
				(deathZAPZAP C_DIE_ZAPZAP)
				(deathWORTHTRY C_DIE_WORTHTRY)
				(deathLEAVEFORMATTING C_DIE_LEAVE_FORMATTING)
				(deathDROIDBLAST C_DIE_KNEWCOMING)
				(deathSPEARED C_DIE_STIFF)
				(deathFALLHANGAR C_DIE_FALL_HANGAR)
				(deathDROIDTAGGED C_DIE_POCKETPAL)
				(deathALLOVER C_DIE_ALLOVER)
				(deathMALLSECURED C_DIE_MALLSECURED)
				(deathLEAVEMALL C_DIE_LEAVEMALL)
				(deathSLUG C_DIE_SLUG)
				(deathSPLEEN C_DIE_SPLEEN)
				(deathCHANGEMACHINE C_DIE_CHANGE_MACHINE)
				(deathORBS C_DIE_ORBS)
			)
		)
		(Message MsgGet EGODEAD 99 0 case 1 @str)
		(switch deathView
			(iconDEAD
				(self setScript: deadScript)
			)
			(iconLIGHTNING
				(self setScript: lightningScript)
			)
			(iconSPEAR
				(self setScript: spearScript)
			)
			(iconSLIME
				(self setScript: slimeScript)
			)
			(iconSLUG
				(self setScript: slugScript)
			)
			(iconMELTED
				(self setScript: meltedScript)
			)
			(iconEXPLODE
				(self setScript: explodeScript)
			)
			(iconDEAD_DRESS
				(self setScript: deadScript 0 4)
			)
			(iconLASER
				(self setScript: laserScript)
			)
			(iconLASER_DRESS
				(self setScript: laserScript 0 4)
			)
			(else 
				(self setScript: deadScript)
				(= case 0)
			)
		)
		(DisplayDeath @str)
		(music number: 826 vol: 127 loop: 1 flags: 1 playBed:)
		(if (self script?)
			((self script?) setScript: voiceODeathScript 0 case)
		)
	)
	
;;;	(method (cue &tmp [str 100])
;;;		(asm
;;;			pushi    #cue
;;;			pushi    0
;;;			super    SQRoom,  4
;;;			pushi    0
;;;			callb    HandsOn,  0
;;;			pushi    #setCursor
;;;			pushi    2
;;;			pushi    999
;;;			pushi    1
;;;			lag      theGame
;;;			send     8
;;;code_0387:
;;;			pushi    15
;;;			lofsa    {LOOKUP\_ERROR}
;;;			push    
;;;			pushi    64
;;;			pushi    83
;;;			pushi    144
;;;			pushi    27
;;;			pushi    1
;;;			pushi    78
;;;			lofsa    {Restore}
;;;			push    
;;;			pushi    0
;;;			pushi    78
;;;			lofsa    {Restart}
;;;			push    
;;;			pushi    1
;;;			pushi    78
;;;			lofsa    {Quit}
;;;			push    
;;;			pushi    2
;;;			calle    SQ4Print,  30
;;;			push    
;;;			dup     
;;;			ldi      0
;;;			eq?     
;;;			bnt      code_03c2
;;;			pushi    #restore
;;;			pushi    0
;;;			lag      theGame
;;;			send     4
;;;			jmp      code_03dd
;;;code_03c2:
;;;			dup     
;;;			ldi      1
;;;			eq?     
;;;			bnt      code_03d1
;;;			pushi    #restart
;;;			pushi    0
;;;			lag      theGame
;;;			send     4
;;;			jmp      code_03dd
;;;code_03d1:
;;;			dup     
;;;			ldi      2
;;;			eq?     
;;;			bnt      code_03dd
;;;			ldi      1
;;;			sag      quit
;;;			jmp      code_03e0
;;;code_03dd:
;;;			toss    
;;;			jmp      code_0387
;;;code_03e0:
;;;			ret     
;;;		)
;;;	)

	(method (cue &tmp [str 100])
		;EO: this is a reconstruction of the above commented-out
		; assembly code
		(super cue:)
		(HandsOn)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(repeat
			(switch
				(SQ4Print {}
					#at 83 144
					#mode teJustCenter
					#button {Restore} 0
					#button {Restart} 1
					#button {Quit} 2
				)
				(0
					(theGame restore:)
				)
				(1
					(theGame restart:)
				)
				(2
					(= quit TRUE)
					(break)
				)
			)
		)		
	)
)

(instance voiceODeathScript of Script

	(method (changeState newState &tmp [str 100])
		(switch (= state newState)
			(0
				(= seconds 2)
			)
			(1
				(voiceOdeath modeless: TRUE say: register self)
			)
			(2
				(curRoom cue:)
				(self dispose:)
			)
		)
	)
)

(instance deadScript of Script
	(properties)
	
	(method (changeState newState &tmp [str 100])
		(switch (= state newState)
			(0
				(egoView
					loop: (+ (egoView loop?) register)
					x: (+ (egoView x?) -2)
					y: (+ (egoView y?) 10)
					init:
				)
				(egosHead
					loop: (+ (egosHead loop?) register)
					x: (+ (egosHead x?) -2)
					y: (+ (egosHead y?) 10)
					init:
				)
				(leftHand
					x: (+ (leftHand x?) -2)
					y: (+ (leftHand y?) 10)
					init:
				)
				(rightHand
					x: (+ (rightHand x?) -2)
					y: (+ (rightHand y?) 10)
					init:
				)
				(= cycles 1)
			)
			(1
				(egosHead setCycle: CycleTo 1 1 self)
				(leftHand setCycle: EndLoop)
				(rightHand setCycle: EndLoop)
			)
			(2
				(egosHead setCycle: BegLoop self)
			)
			(3
				(egosHead setCycle: EndLoop self)
				(rightHand setCycle: BegLoop)
			)
			(4
				(rightHand setCycle: EndLoop self)
			)
			(5 0)
			(6
				(self dispose:)
			)
		)
	)
)

(instance lightningScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(egoView
					x: (+ (egoView x?) -2)
					y: (+ (egoView y?) 10)
					init:
				)
				(egosHead
					x: (+ (egosHead x?) -2)
					y: (+ (egosHead y?) 10)
					init:
				)
				(leftHand
					x: (+ (leftHand x?) -2)
					y: (+ (leftHand y?) 10)
					init:
				)
				(rightHand
					x: (+ (rightHand x?) -2)
					y: (+ (rightHand y?) 10)
					init:
				)
				((lightningProp new:) init: x: 67 y: 100)
				((lightningProp new:) init: x: 152 y: 99 loop: 1)
				((lightningProp new:) x: 126 y: 90 loop: 1 cel: 2 init:)
				((lightningProp new:) x: 93 y: 86 cel: 2 init:)
				((lightningProp new:) x: 55 y: 87 loop: 1 cel: 4 init:)
				(= seconds 3)
			)
			(1
				(egosHead setCycle: CycleTo 1 1 self)
				(leftHand setCycle: EndLoop)
				(rightHand setCycle: EndLoop)
			)
			(2
				(egosHead setCycle: BegLoop self)
			)
			(3
				(egosHead setCycle: EndLoop self)
				(rightHand setCycle: BegLoop)
			)
			(4
				(rightHand setCycle: EndLoop self)
			)
		)
	)
)

(instance spearScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(egoView
					x: (+ (egoView x?) -2)
					y: (+ (egoView y?) 10)
					init:
				)
				(egosHead
					x: (+ (egosHead x?) -2)
					y: (+ (egosHead y?) 10)
					init:
				)
				(leftHand
					x: (+ (leftHand x?) -2)
					y: (+ (leftHand y?) 10)
					init:
				)
				(rightHand
					x: (+ (rightHand x?) -2)
					y: (+ (rightHand y?) 10)
					init:
				)
				(deathProp view: 985 x: 90 y: 85 priority: 12 init:)
				(= cycles 1)
			)
			(1
				(egosHead setCycle: CycleTo 1 1 self)
				(leftHand setCycle: EndLoop)
				(rightHand setCycle: EndLoop)
			)
			(2
				(egosHead setCycle: BegLoop self)
			)
			(3
				(egosHead setCycle: EndLoop self)
				(rightHand setCycle: BegLoop)
			)
			(4
				(rightHand cycleSpeed: 6 setCycle: EndLoop self)
			)
			(5
				(leftHand cycleSpeed: 6 setCycle: BegLoop self)
			)
			(6 0)
			(7 (self dispose:))
		)
	)
)

(instance slimeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(deathProp view: 986 cel: 0 x: 96 y: 127 init:)
				(= cycles 1)
			)
			(1 0)
			(2 (self dispose:))
		)
	)
)

(instance slugScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(deathProp view: 986 cel: 1 x: 96 y: 127 init:)
				(= cycles 1)
			)
			(1 0)
			(2 (self dispose:))
		)
	)
)

(instance meltedScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(egoView view: 987 x: 106 y: 116 init:)
				(deathProp
					view: 987
					loop: 1
					x: 92
					y: 99
					setCycle: Forward
					init:
				)
				(= cycles 1)
			)
			(1
				(globalSound number: 90 loop: -1 vol: 105 play:)
				(= cycles 39)
			)
			(2
				(deathProp setCycle: EndLoop self)
			)
			(3 0)
			(4 (self dispose:))
		)
	)
)

(instance explodeScript of Script
	(properties)
	
	(method (changeState newState &tmp [str 100])
		(switch (= state newState)
			(0
				(egosHead x: 83 y: 102 cel: 2 setCycle: EndLoop init:)
				(leftHand
					x: 49
					y: 60
					setCycle: Walk
					setMotion: DPath 53 72 39 93 76 80 86 90 self
					init:
				)
				(rightHand
					x: 98
					y: 80
					setCycle: Walk
					setMotion: DPath 118 65 141 78 131 94 157 124 self
					init:
				)
			)
			(1 0)
			(2 (self dispose:))
		)
	)
)

(instance laserScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(egoView
					loop: (+ (egoView loop?) register)
					x: (+ (egoView x?) -2)
					y: (+ (egoView y?) 10)
					init:
				)
				(egosHead
					loop: (+ (egosHead loop?) register)
					x: (+ (egosHead x?) -2)
					y: (+ (egosHead y?) 10)
					init:
				)
				(leftHand
					x: (+ (leftHand x?) -2)
					y: (+ (leftHand y?) 10)
					init:
				)
				(rightHand
					x: (+ (rightHand x?) -2)
					y: (+ (rightHand y?) 10)
					init:
				)
				(deathProp
					view: 985
					x: 100
					y: 83
					setCel: (+ 1 (if register 1 else 0))
					setPri: 7
					init:
				)
				(smoke
					x: (+ (smoke x?) -2)
					y: (+ (smoke y?) 10)
					setCycle: Forward
					init:
				)
				(= cycles 1)
			)
			(1
				(egosHead setCycle: CycleTo 1 1 self)
				(leftHand setCycle: EndLoop)
				(rightHand setCycle: EndLoop)
			)
			(2
				(egosHead setCycle: BegLoop self)
			)
			(3
				(egosHead setCycle: EndLoop self)
				(rightHand setCycle: BegLoop)
			)
			(4
				(rightHand cycleSpeed: 6 setCycle: EndLoop self)
			)
			(5
				(leftHand cycleSpeed: 6 setCycle: BegLoop self)
			)
			(6 (= cycles 10))
			(7 0)
			(8 (self dispose:))
		)
	)
)

(instance egoView of Sq4View
	(properties
		x 97
		y 115
		view 805
		priority 6
		signal (| ignrAct fixPriOn)
	)
)

(instance egosHead of Sq4Prop
	(properties
		x 104
		y 80
		view 805
		loop 1
		priority 8
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
)

(instance rightHand of Sq4Actor
	(properties
		x 84
		y 111
		yStep 1
		view 805
		loop 2
		priority 10
		signal (| ignrAct fixPriOn fixedLoop)
		cycleSpeed 12
		illegalBits $0000
		xStep 1
	)
)

(instance leftHand of Sq4Actor
	(properties
		x 146
		y 104
		yStep 1
		view 805
		loop 3
		priority 10
		signal (| ignrAct fixPriOn fixedLoop)
		cycleSpeed 12
		xStep 1
	)
)

(instance deathProp of Sq4Prop
	(properties
		priority 12
		signal (| ignrAct fixPriOn)
	)
)

(class Shocks of Sq4Prop
	(properties
		view 984
		loop 0
		cel 0
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init:)
		(self setCycle: ForwardCounter 10 self)
	)
	
	(method (cue)
		(self hide: dispose:)
		(globalSound stop:)
		(super cue:)
		(curRoom cue:)
	)
)

(instance lightningProp of Shocks
	(properties)
)

(instance smoke of Sq4Prop
	(properties
		x 102
		y 73
		view 985
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance voiceOdeath of Sq4Narrator
	(properties
		noun 99
		modeless TRUE
		nMsgType CD_MSG
	)
	
	(method (display)
	)
)
