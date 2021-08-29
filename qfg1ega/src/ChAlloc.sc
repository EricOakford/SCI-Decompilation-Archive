;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARALLOC) ;203
(include game.sh)
(use Main)
(use StatusBar)
(use Intrface)
(use Game)

(public
	chAlloc 0
)

(define NOSAVE	1)		; Don't save underbits

(local
	pointsAvail
	origAvail
	[str 80]
)
(procedure (SaveStats &tmp i)
	(for ((= i 0)) (< i NUMSTATS) ((++ i))
		(= [oldStats i] [egoStats i])
	)
)

(procedure (ShowValue x y color)
	(Display &rest
		100 x y ;CI: NOTE: SCICompanion will not compile if a #define is immediately after a &rest modifier
		p_mode teJustLeft
		p_font 300
		p_color color
		p_back vWHITE
	)
)

(procedure (UpdatePoints)
	(ShowValue 40 127
		sameColor
		(Format @str 203 0 pointsAvail origAvail)
		p_width 52
	)
	;%d / %d
	(poolBar max: origAvail value: pointsAvail draw:)
	(RedrawCast)
)


(instance nameEdit of DEdit
	(properties
		nsTop 6
		nsLeft 58
		nsBottom 19
		nsRight 310
		font 300
		max 40
	)
	
	(method (handleEvent event &tmp ret)
		(if (event claimed?) (return FALSE))
		(if (& state dSelected)
			(if (== direction (event type?))
				(event type: keyDown)
				(switch (event message?)
					(dirS
						(event message: DOWNARROW)
					)
					(dirN (event message: UPARROW))
				)
			)
			(if (and (& state dSelected) (== keyDown (event type?)))
				(switch (event message?)
					(DOWNARROW
						(event message: TAB)
					)
					(UPARROW
						(event message: SHIFTTAB)
					)
				)
			)
		)
		(return
			(super handleEvent: event)
		)
	)
)

(class BoxSelector of DItem
	(properties
		type 10	;is custom to QFG
		state dActive
	)
	
	(method (handleEvent event &tmp ret)
		(if (event claimed?) (return FALSE))
		(if (= ret (super handleEvent: event))
			(return ret)
		)
		(if (not (& state dSelected)) (return FALSE))
		(if (== direction (event type?))
			(event type: keyDown)
			(switch (event message?)
				(dirS
					(event message: DOWNARROW)
				)
				(dirN
					(event message: UPARROW)
				)
				(dirE
					(event message: RIGHTARROW)
				)
				(dirW
					(event message: LEFTARROW)
				)
				(else
					(event type: direction)
				)
			)
		)
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(UPARROW
						(event message: SHIFTTAB)
					)
					(DOWNARROW
						(event message: TAB)
					)
					(RIGHTARROW
						(self incr: 5)
					)
					(LEFTARROW
						(self dec: 5)
					)
					(`+
						(self incr: 1)
					)
					(`-
						(self dec: 1)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		)
		(return FALSE)
	)
	
	(method (draw)
		(DrawCel 802 1
			(if (& state dSelected) 1 else 2)
			nsLeft
			nsTop -1
		)
	)
	
	(method (incr amt)
		(if (not pointsAvail) (return))
		(if (< pointsAvail amt)
			(= amt pointsAvail)
		)
		(if (== [egoStats value] 0)
			(if (< pointsAvail 15) (return))
			(= [egoStats value] 5)
			(-= pointsAvail 15)
		else
			(+= [egoStats value] amt)
			(-= pointsAvail amt)
		)
		(self select: 0)
		(= [egoStats HEALTH] (MaxHealth))
		(= [egoStats STAMINA] (MaxStamina))
		(= [egoStats MANA] (MaxMana))
		(UpdatePoints)
		((ScriptID 204 0) update:)
		(self select: 1)
	)
	
	(method (dec amt &tmp delta)
		(if
			(not
				(= delta (- [egoStats value] [oldStats value]))
			)
			;the skill is already at it's baseline (for this heroType), so nothing to decrease.
			(return)
		)
		;CI: NOTE: there is a bug, where if the stat is 6 or higher, 
		;you can remove 5 and skip the 15-point pool allocation
		(if (== [egoStats value] 5)
			(= [egoStats value] 0)
			(+= pointsAvail 15)
		else
			(if (< delta amt)
				(= amt delta)
			)
			(-= [egoStats value] amt)
			(+= pointsAvail amt)
		)
		(self select: FALSE)
		(= [egoStats HEALTH] (MaxHealth))
		(= [egoStats STAMINA] (MaxStamina))
		(= [egoStats MANA] (MaxMana))
		(UpdatePoints)
		((ScriptID 204 0) update:)
		(self select: TRUE)
	)
)


(instance strengthSel of BoxSelector
	(properties
		nsTop 33
		nsLeft 81
		nsBottom 46
		nsRight 191
	)
)

(instance intSel of BoxSelector
	(properties
		nsTop 45
		nsLeft 81
		nsBottom 58
		nsRight 191
		value 1
	)
)

(instance agilSel of BoxSelector
	(properties
		nsTop 57
		nsLeft 81
		nsBottom 70
		nsRight 191
		value 2
	)
)

(instance vitSel of BoxSelector
	(properties
		nsTop 69
		nsLeft 81
		nsBottom 82
		nsRight 191
		value 3
	)
)

(instance luckSel of BoxSelector
	(properties
		nsTop 81
		nsLeft 81
		nsBottom 94
		nsRight 191
		value 4
	)
)

(instance wpnSel of BoxSelector
	(properties
		nsTop 26
		nsLeft 205
		nsBottom 39
		nsRight 315
		value 5
	)
)

(instance parrySel of BoxSelector
	(properties
		nsTop 38
		nsLeft 205
		nsBottom 51
		nsRight 315
		value 6
	)
)

(instance dodgeSel of BoxSelector
	(properties
		nsTop 50
		nsLeft 205
		nsBottom 63
		nsRight 315
		value 7
	)
)

(instance stealthSel of BoxSelector
	(properties
		nsTop 62
		nsLeft 205
		nsBottom 75
		nsRight 315
		value 8
	)
)

(instance pickSel of BoxSelector
	(properties
		nsTop 74
		nsLeft 205
		nsBottom 87
		nsRight 315
		value 9
	)
)

(instance throwSel of BoxSelector
	(properties
		nsTop 86
		nsLeft 205
		nsBottom 99
		nsRight 315
		value 10
	)
)

(instance climbSel of BoxSelector
	(properties
		nsTop 98
		nsLeft 205
		nsBottom 111
		nsRight 315
		value 11
	)
)

(instance magicSel of BoxSelector
	(properties
		nsTop 110
		nsLeft 205
		nsBottom 123
		nsRight 315
		value 12
	)
)

(class CSDButton of DButton
	(method (handleEvent event &tmp ret)
		(if (event claimed?) (return FALSE))

		(if (& state dSelected)
			(if (== direction (event type?))
				(event type: keyDown)
				(switch (event message?)
					(dirS
						(event message: DOWNARROW)
					)
					(dirN 
						(event message: UPARROW)
					)
				)
			)
			(if (and (& state dSelected) (== keyDown (event type?)))
				(switch (event message?)
					(DOWNARROW
						(event message: TAB)
					)
					(UPARROW 
						(event message: SHIFTTAB)
					)
				)
			)
		)
		(return
			(super handleEvent: event)
		)
	)
)

(instance exitButton of CSDButton
	(properties
		nsTop 156
		nsLeft 210
		text { Start Game_}
	)
)

(instance canButton of CSDButton
	(properties
		nsTop 171
		nsLeft 226
		text { Cancel_}
	)
)

(instance statDial of Dialog
	(properties
		nsBottom 189
		nsRight 319
	)
)

(instance poolBar of StatusBar
	(properties
		x 95
		y 132
		titleCel 4
	)
)

(instance chAlloc of Room
	(properties
		picture 401
		style PLAIN
	)
	
	(method (init &tmp answer looping nextRoom)
		(Load SCRIPT 204)
		(Load SCRIPT 205)
		(Load VIEW vCharSheet)
		(Load VIEW statusBarView)
		(super init:)
		(cSound stop:)
		
		;set up initial experience and money
		(= [egoStats EXPER] 0)
		(= [invNum iSilver] 10)
		(= [invNum iGold] 4)
		
		;clear items if somebody's restarting
		(ego
			use: iSword
			use: iShield
			use: iDagger
			use: iLockPick
			use: iLeather
			use: iRations 5
		)
		(= userName 0)
		(= pointsAvail 50)
		
		;set the base stats for everyone
		(= [egoStats OPEN] 0)
		(= [egoStats DETMAGIC] 0)
		(= [egoStats ZAP] 0)
		(= [egoStats FLAMEDART] 0)
		(= [egoStats FETCH] 0)
		
		;set up specific hero type's stats
		(switch heroType
			(FIGHTER
				(= [egoStats STR]     25)
				(= [egoStats INT]     10)
				(= [egoStats AGIL]    15)
				(= [egoStats VIT]     15)
				(= [egoStats LUCK]    10)
				
				(= [egoStats WEAPON]  20)
				(= [egoStats PARRY]   15)
				(= [egoStats DODGE]   10)
				(= [egoStats STEALTH]  0)
				(= [egoStats PICK]     0)
				(= [egoStats THROW]   10)
				(= [egoStats CLIMB]    0)
				(= [egoStats MAGIC]    0)

				(ego
					get: iSword
					get: iShield
				)
			)
			(MAGIC_USER
				(= [egoStats STR]     10)
				(= [egoStats INT]     25)
				(= [egoStats AGIL]    15)
				(= [egoStats VIT]     15)
				(= [egoStats LUCK]    10)
				
				(= [egoStats WEAPON]  10)
				(= [egoStats PARRY]    0)
				(= [egoStats DODGE]   15)
				(= [egoStats STEALTH]  0)
				(= [egoStats PICK]     0)
				(= [egoStats THROW]    0)
				(= [egoStats CLIMB]    0)
				(= [egoStats MAGIC]   25)

				(= [egoStats ZAP] 	  10)
				(ego get: iDagger)
			)
			(THIEF
				(= [egoStats STR]     10)
				(= [egoStats INT]     15)
				(= [egoStats AGIL]    25)
				(= [egoStats VIT]     10)
				(= [egoStats LUCK]    10)
				
				(= [egoStats WEAPON]  10)
				(= [egoStats PARRY]    0)
				(= [egoStats DODGE]    5)
				(= [egoStats STEALTH] 10)
				(= [egoStats PICK]    10)
				(= [egoStats THROW]    5)
				(= [egoStats CLIMB]    5)
				(= [egoStats MAGIC]    0)

				(ego
					get: iDagger
					get: iLockPick
				)
				(= lockPickBonus 10)
			)
		)
		
		(= origAvail pointsAvail)
		(= [egoStats HEALTH] (MaxHealth))
		(= [egoStats STAMINA] (MaxStamina))
		(= [egoStats MANA] (MaxMana))
		(ego get: iLeather get: iRations 5)
		(SaveStats)
		
		;not sure if this is correct or not, based on ASM
		;EO: I've made corrections
		(nameEdit
			text: @userName
			cursor: (StrLen @userName))
		(exitButton setSize:)
		(canButton setSize:)
		(statDial window: systemWindow
			add: nameEdit strengthSel intSel agilSel vitSel luckSel
			wpnSel parrySel dodgeSel stealthSel pickSel throwSel
			climbSel magicSel exitButton canButton
			open: (| stdWindow NOSAVE) -1 ;vBLACK vWHITE ;EO: Fixed incorrect code
		)
		
		((ScriptID 204 0) useWindow: FALSE showBars: FALSE init:)
		(Display 203 1
			p_at 62 109
			p_mode teJustLeft
			p_font 300
			p_color changeColor
		)
		;Points Available
		(poolBar max: origAvail value: pointsAvail init:)
		(Display 203 2
			p_at 165 127
			p_width 150
			p_mode teJustLeft
			p_font 300
			p_color changeColor
		)
		;TAB to move around,\nArrows to adjust values.
		
		(UpdatePoints)
		(RedrawCast)
		
		(theGame setCursor: normalCursor TRUE)
		
		(= looping TRUE)
		(= nextRoom 300) ;TownOutlook
		(while looping
			(= answer (statDial doit:))
			(cond 
				((== answer canButton)
					;Choose a new character type?
					(if (== (Print 203 3 #button {Yes} 1 #button {No} 2) 1)
						(= nextRoom 202)
						(= looping FALSE)
					)
				)
				((> pointsAvail 0)
					;You still have points to allocate.
					(if (== (Print 203 4 #button {Start the Game} 1 #button {Use More Points} 2) 1)
						(= looping FALSE)
					)
				)
				(else
					(= looping FALSE)
				)
			)
		)
		;finalize 
		(HandsOff)
		(= [egoStats HEALTH] (MaxHealth))
		(= [egoStats STAMINA] (MaxStamina))
		(= [egoStats MANA] (MaxMana))
		(SaveStats)
		(poolBar dispose:)
		((ScriptID CHARSHEET 0) dispose:)
		(statDial dispose:)
		(addToPics dispose:)

		;now move either to the TownOutlook, or back to the Character Select screen
		(curRoom drawPic: 401 newRoom: nextRoom); pic401 is a white screen
	)
	
;;; 	(method (init &tmp temp0 temp1 temp2)
;;; 		(asm
;;; 			pushi    2
;;; 			pushi    RES_SCRIPT
;;; 			pushi    204
;;; 			callk    Load,  4			;(Load RES_SCRIPT 204)
;;; 			pushi    2
;;; 			pushi    RES_SCRIPT
;;; 			pushi    205
;;; 			callk    Load,  4			;(Load RES_SCRIPT 205)
;;; 			pushi    2
;;; 			pushi    RES_VIEW
;;; 			pushi    vEgoCharSheet
;;; 			callk    Load,  4			;(Load RES_VIEW vEgoCharSheet)
;;; 			pushi    2
;;; 			pushi    RES_VIEW
;;; 			lsg      hpStatusView
;;; 			callk    Load,  4			;(Load RES_VIEW hpStatusView)
;;; 			pushi    #init
;;; 			pushi    0
;;; 			super    Room,  4			;(super init:)
;;; 			pushi    #stop
;;; 			pushi    0
;;; 			lag      cSound				;(cSound stop:)
;;; 			send     4
;;; 			pushi    0
;;; 			ldi      EXPER
;;; 			sagi     egoStats			;(= [egoStats EXPER] 0)
;;; 			pushi    10
;;; 			ldi      iSilver
;;; 			sagi     invNum				;(= [invNum iSilver] 10)
;;; 			pushi    4
;;; 			ldi      iGold
;;; 			sagi     invNum				;(= [invNum iGold] 4)
;;; 			pushi    #use
;;; 			pushi    1
;;; 			pushi    iSword
;;; 			pushi    #use
;;; 			pushi    1
;;; 			pushi    iShield
;;; 			pushi    #use
;;; 			pushi    1
;;; 			pushi    iDagger
;;; 			pushi    #use
;;; 			pushi    1
;;; 			pushi    iLockPick
;;; 			pushi    #use
;;; 			pushi    1
;;; 			pushi    iLeather
;;; 			pushi    #use
;;; 			pushi    2
;;; 			pushi    iRations
;;; 			pushi    5
;;; 			lag      ego				;?? (ego use: iSword use: iShield use: iDagger use: iLockPick use: iLeather use: iRations 5)
;;; 			send     38
;;; 			ldi      0
;;; 			sag      userName			;(= userName NULL)
;;; 			ldi      50
;;; 			sal      pointsAvail		;(= pointsAvail 50)
;;; 			pushi    0
;;; 			ldi      OPEN
;;; 			sagi     egoStats			;(= [egoStats OPEN] 0)
;;; 			pushi    0
;;; 			ldi      DETMAGIC
;;; 			sagi     egoStats			;(= [egoStats DETMAGIC] 0)
;;; 			pushi    0
;;; 			ldi      ZAP
;;; 			sagi     egoStats			;(= [egoStats ZAP] 0)
;;; 			pushi    0
;;; 			ldi      FLAMEDART
;;; 			sagi     egoStats			;(= [egoStats FLAMEDART] 0)
;;; 			pushi    0
;;; 			ldi      FETCH
;;; 			sagi     egoStats			;(= [egoStats FETCH] 0)
;;; 			lsg      heroType			;(switch heroType
;;; 			dup     
;;; 			ldi      FIGHTER			;	(FIGHTER
;;; 			eq?     
;;; 			bnt      code_08f1
;;; 			pushi    25
;;; 			ldi      STR
;;; 			sagi     egoStats			;		(= [egoStats STR] 25)
;;; 			pushi    10
;;; 			ldi      INT
;;; 			sagi     egoStats			;		(= [egoStats INT]     10)
;;; 			pushi    15
;;; 			ldi      AGIL
;;; 			sagi     egoStats			;		(= [egoStats AGIL]    15)
;;; 			pushi    15
;;; 			ldi      VIT
;;; 			sagi     egoStats			;		(= [egoStats VIT]     15)
;;; 			pushi    10
;;; 			ldi      LUCK
;;; 			sagi     egoStats			;		(= [egoStats LUCK]    10)
;;; 			pushi    20
;;; 			ldi      WEAPON
;;; 			sagi     egoStats			;		(= [egoStats WEAPON]  20)
;;; 			pushi    15
;;; 			ldi      PARRY
;;; 			sagi     egoStats			;		(= [egoStats PARRY]   15)
;;; 			pushi    10
;;; 			ldi      DODGE
;;; 			sagi     egoStats			;		(= [egoStats DODGE]   10)
;;; 			pushi    0
;;; 			ldi      STEALTH
;;; 			sagi     egoStats			;		(= [egoStats STEALTH]  0)
;;; 			pushi    0
;;; 			ldi      PICK
;;; 			sagi     egoStats			;		(= [egoStats PICK]     0)
;;; 			pushi    10
;;; 			ldi      THROW
;;; 			sagi     egoStats			;		(= [egoStats THROW]   10
;;; 			pushi    0
;;; 			ldi      CLIMB
;;; 			sagi     egoStats			;		(= [egoStats CLIMB]    0)
;;; 			pushi    0
;;; 			ldi      MAGIC
;;; 			sagi     egoStats			;		(= [egoStats MAGIC]    0)
;;; 			pushi    #get
;;; 			pushi    1
;;; 			pushi    iSword
;;; 			pushi    #get
;;; 			pushi    1
;;; 			pushi    iShield
;;; 			lag      ego				;		(ego get: iSword get: iShield)
;;; 			send     12
;;; 			jmp      code_09bb			;	)
;;; code_08f1:
;;; 			dup     
;;; 			ldi      MAGE				;	(MAGE
;;; 			eq?     
;;; 			bnt      code_0954
;;; 			pushi    10
;;; 			ldi      STR
;;; 			sagi     egoStats
;;; 			pushi    25
;;; 			ldi      INT
;;; 			sagi     egoStats
;;; 			pushi    15
;;; 			ldi      AGIL
;;; 			sagi     egoStats
;;; 			pushi    15
;;; 			ldi      VIT
;;; 			sagi     egoStats
;;; 			pushi    10
;;; 			ldi      LUCK
;;; 			sagi     egoStats
;;; 			pushi    10
;;; 			ldi      WEAPON
;;; 			sagi     egoStats
;;; 			pushi    0
;;; 			ldi      PARRY
;;; 			sagi     egoStats
;;; 			pushi    15
;;; 			ldi      DODGE
;;; 			sagi     egoStats
;;; 			pushi    0
;;; 			ldi      STEALTH
;;; 			sagi     egoStats
;;; 			pushi    0
;;; 			ldi      PICK
;;; 			sagi     egoStats
;;; 			pushi    0
;;; 			ldi      THROW
;;; 			sagi     egoStats
;;; 			pushi    0
;;; 			ldi      CLIMB
;;; 			sagi     egoStats
;;; 			pushi    25
;;; 			ldi      MAGIC
;;; 			sagi     egoStats
;;; 			pushi    10
;;; 			ldi      ZAP
;;; 			sagi     egoStats	
;;; 			pushi    #get
;;; 			pushi    1
;;; 			pushi    iDagger
;;; 			lag      ego
;;; 			send     6
;;; 			jmp      code_09bb			;	)
;;; code_0954:
;;; 			dup     
;;; 			ldi      THIEF				;	(THIEF
;;; 			eq?     
;;; 			bnt      code_09bb
;;; 			pushi    10
;;; 			ldi      STR
;;; 			sagi     egoStats
;;; 			pushi    15
;;; 			ldi      INT
;;; 			sagi     egoStats
;;; 			pushi    25
;;; 			ldi      AGIL
;;; 			sagi     egoStats
;;; 			pushi    10
;;; 			ldi      VIT
;;; 			sagi     egoStats
;;; 			pushi    10
;;; 			ldi      LUCK
;;; 			sagi     egoStats
;;; 			pushi    10
;;; 			ldi      WEAPON
;;; 			sagi     egoStats
;;; 			pushi    0
;;; 			ldi      PARRY
;;; 			sagi     egoStats
;;; 			pushi    5
;;; 			ldi      DODGE
;;; 			sagi     egoStats
;;; 			pushi    10
;;; 			ldi      STEALTH
;;; 			sagi     egoStats
;;; 			pushi    10
;;; 			ldi      PICK
;;; 			sagi     egoStats
;;; 			pushi    5
;;; 			ldi      THROW
;;; 			sagi     egoStats
;;; 			pushi    5
;;; 			ldi      CLIMB
;;; 			sagi     egoStats
;;; 			pushi    0
;;; 			ldi      MAGIC
;;; 			sagi     egoStats
;;; 			pushi    #get
;;; 			pushi    1
;;; 			pushi    iDagger
;;; 			pushi    #get
;;; 			pushi    1
;;; 			pushi    iLockPick
;;; 			lag      ego			;		(ego get: iDagger get: iLockPick)
;;; 			send     12
;;; 			ldi      10
;;; 			sag      lockPickBonus	;		(= lockPickBonus 10)
;;; 									;	)
;;; code_09bb:
;;; 			toss    				;)
;;; 			lal      pointsAvail
;;; 			sal      origAvail			;(= origAvail pointsAvail)
;;; 			pushi    0
;;; 			callb    MaxHealth,  0
;;; 			push    
;;; 			ldi      HEALTH
;;; 			sagi     egoStats			;(= [egoStats HEALTH] (MaxHealth))
;;; 			pushi    0
;;; 			callb    MaxStamina,  0
;;; 			push    
;;; 			ldi      STAMINA
;;; 			sagi     egoStats			;(= [egoStats STAMINA] (MaxStamina))
;;; 			pushi    0
;;; 			callb    MaxMana,  0
;;; 			push    
;;; 			ldi      MANA
;;; 			sagi     egoStats			;(= [egoStats MANA] (MaxMana))
;;; 			pushi    #get
;;; 			pushi    1
;;; 			pushi    iLeather
;;; 			pushi    #get
;;; 			pushi    2
;;; 			pushi    iRations
;;; 			pushi    5
;;; 			lag      ego				;(ego get: iLeather get: iRations 5)
;;; 			send     14
;;;
;;; 			pushi    0
;;; 			call     SaveStats,  0	;(SaveStats)
;;;
;;; 			pushi    #text
;;; 			pushi    1
;;; 			lea      @userName
;;; 			push    
;;; 			pushi    #cursor
;;; 			pushi    1
;;; 			pushi    1
;;; 			lea      @userName
;;; 			push    
;;; 			callk    StrLen,  2
;;; 			push    
;;; 			lofsa    nameEdit			;(nameEdit cursor: (StrLen @userName) text: @userName)
;;; 			send     12
;;;
;;; 			pushi    #setSize
;;; 			pushi    0
;;; 			lofsa    exitButton			;(exitButton setSize:)
;;; 			send     4
;;;
;;; 			pushi    #setSize
;;; 			pushi    0
;;; 			lofsa    canButton			;(canButton setSize:)
;;; 			send     4
;;;
;;; 			pushi    #window
;;; 			pushi    1
;;; 			lsg      systemWindow
;;; 			pushi    #add
;;; 			pushi    16
;;; 			lofsa    nameEdit
;;; 			push    
;;; 			lofsa    strengthSel
;;; 			push    
;;; 			lofsa    intSel
;;; 			push    
;;; 			lofsa    agilSel
;;; 			push    
;;; 			lofsa    vitSel
;;; 			push    
;;; 			lofsa    luckSel
;;; 			push    
;;; 			lofsa    wpnSel
;;; 			push    
;;; 			lofsa    parrySel
;;; 			push    
;;; 			lofsa    dodgeSel
;;; 			push    
;;; 			lofsa    stealthSel
;;; 			push    
;;; 			lofsa    pickSel
;;; 			push    
;;; 			lofsa    throwSel
;;; 			push    
;;; 			lofsa    climbSel
;;; 			push    
;;; 			lofsa    magicSel
;;; 			push    
;;; 			lofsa    exitButton
;;; 			push    
;;; 			lofsa    canButton
;;; 			push    
;;; 			pushi    #open
;;; 			pushi    2
;;; 			pushi    vBLACK
;;; 			pushi    vWHITE
;;; 			lofsa    statDial	;(statDial window: systemWindow add: nameEdit strengthSel intSel agilSel vitSel luckSel wpnSel parrySel dodgeSel stealthSel pickSel throwSel climbSel magicSel exitButton canButton open: vBLACK vWHITE)
;;; 			send     50
;;;
;;; 			pushi    #useWindow
;;; 			pushi    1
;;; 			pushi    FALSE
;;; 			pushi    #showBars
;;; 			pushi    1
;;; 			pushi    FALSE
;;; 			pushi    #init
;;; 			pushi    0
;;; 			pushi    2
;;; 			pushi    204
;;; 			pushi    0
;;; 			callk    ScriptID,  4	;((ScriptID 204 0) useWindow: FALSE showBars: FALSE init:)
;;; 			send     16
;;;
;;; 			pushi    11
;;; 			pushi    203
;;; 			pushi    1
;;; 			pushi    p_at
;;; 			pushi    62
;;; 			pushi    109
;;; 			pushi    p_mode
;;; 			pushi    0
;;; 			pushi    p_font
;;; 			pushi    300
;;; 			pushi    p_color
;;; 			lsg      changeColor
;;; 			callk    Display,  22	;(Display 203 1 p_at 62 109 p_mode 0 p_font 300 p_color changeColor)
;;; 										;Points Available
;;; 			pushi    #max
;;; 			pushi    1
;;; 			lsl      origAvail
;;; 			pushi    #value
;;; 			pushi    1
;;; 			lsl      pointsAvail
;;; 			pushi    #init
;;; 			pushi    0
;;; 			lofsa    poolBar		;(poolBar max: origAvail value: pointsAvail init:)
;;; 			send     16
;;;
;;; 			pushi    13
;;; 			pushi    203
;;; 			pushi    2
;;; 			pushi    p_at
;;; 			pushi    165
;;; 			pushi    127
;;; 			pushi    p_width
;;; 			pushi    150
;;; 			pushi    p_mode
;;; 			pushi    0
;;; 			pushi    p_font
;;; 			pushi    300
;;; 			pushi    p_color
;;; 			lsg      changeColor
;;; 			callk    Display,  26	;(Display 203 2 p_at 165 127 p_width 150 p_mode 0 p_font 300 p_color changeColor)
;;; 										;TAB to move around,\nArrows to adjust values.
;;;
;;; 			pushi    0
;;; 			call     UpdatePoints,  0	;(UpdatePoints)
;;;
;;; 			pushi    0
;;; 			callb    RedrawCast,  0			;(RedrawCast)
;;;
;;; 			pushi    #setCursor
;;; 			pushi    2
;;; 			lsg      normalCursor
;;; 			pushi    TRUE
;;; 			lag      theGame			;(theGame setCursor: normalCursor TRUE)
;;; 			send     8
;;;		
;;; 			ldi      1
;;; 			sat      temp1		;(= temp1 1)
;;;
;;; 			ldi      300		;(= temp2 300)
;;; 			sat      temp2
;;;
;;; ;start loop
;;; code_0af9:
;;; 			lat      temp1
;;; 			bnt      code_0b7b	;jump to end (if temp1 = 0)
;;;
;;; 			pushi    #doit
;;; 			pushi    0
;;; 			lofsa    statDial	;(statDial doit:)
;;; 			send     4
;;;
;;; 			sat      temp0
;;; 			not     
;;; 			bt       code_0b15	;jump to cancel yes/no
;;;
;;; 			lst      temp0
;;; 			lofsa    canButton	;(= temp0 (canButton)) --- not sure... canButton is CancelButton
;;; 			eq?     
;;; 			bnt      code_0b43	;jump to use more points yes/no
;;;
;;; ;cancel yes/no
;;; code_0b15:
;;; 			pushi    8
;;; 			pushi    203
;;; 			pushi    3
;;; 			pushi    #button
;;; 			lofsa    {Yes}
;;; 			push    
;;; 			pushi    1
;;; 			pushi    #button
;;; 			lofsa    {No}
;;; 			push    
;;; 			pushi    2
;;; 			calle    Print,  16
;;;
;;; 			sat      temp0			;(= temp0 (Print 203 3 #button {Yes} 1 #button {No} 2))
;;; 					;Choose a new character type?
;;;
;;; 			push    
;;; 			ldi      1
;;; 			eq?     				;if temp0 = 1 then we exit the loop
;;; 			bnt      code_0af9
;;; 			ldi      0
;;; 			sat      temp1			;(= temp1 0)
;;; 			ldi      202
;;; 			sat      temp2			;(= temp2 202)
;;; 			jmp      code_0af9		;jump to start of loop and do over
;;;
;;; ;use more points yes/no
;;; code_0b43:
;;; 			lsl      pointsAvail
;;; 			ldi      0
;;; 			gt?     
;;; 			bnt      code_0b74
;;; 			pushi    8
;;; 			pushi    203
;;; 			pushi    4
;;; 			pushi    #button
;;; 			lofsa    {Start the Game}
;;; 			push    
;;; 			pushi    1
;;; 			pushi    #button
;;; 			lofsa    {Use More Points}
;;; 			push    
;;; 			pushi    2
;;; 			calle    Print,  16
;;;
;;; 			sat      temp0			;(= temp0 (Print 203 4 #button {Start the Game} 1 #button {Use More Points} 2))
;;; 					;You still have points to allocate.
;;;
;;; 			push    
;;; 			ldi      1				
;;; 			eq?     				;if temp0 = 1 then, we exit the loop (i.e. start the game)
;;; 			bnt      code_0af9		;(= temp1 1), then jump to start of loop
;;; 			ldi      0
;;; 			sat      temp1			;(= temp1 0)
;;; 			jmp      code_0af9		;set temp = 0 then jump to start of loop, which will jump to end
;;;
;;; ;jump to 2nd last loop
;;; code_0b74:
;;; 			ldi      0
;;; 			sat      temp1			;(= temp1 0) 
;;; 			jmp      code_0af9		;jump to start of loop which will jump to end
;;;
;;; ;end of loop
;;; code_0b7b:
;;;
;;; 			pushi    0
;;; 			callb    HandsOff,  0				;(HandsOff)
;;;
;;; 			pushi    0
;;; 			callb    MaxHealth,  0
;;; 			push    
;;; 			ldi      HEALTH
;;; 			sagi     egoStats					;(= [egoStats HEALTH] (MaxHealth))
;;;
;;; 			pushi    0
;;; 			callb    MaxStamina,  0
;;; 			push    
;;; 			ldi      STAMINA
;;; 			sagi     egoStats					;(= [egoStats STAMINA] (MaxStamina))
;;;
;;; 			pushi    0
;;; 			callb    MaxMana,  0
;;; 			push    
;;; 			ldi      MANA
;;; 			sagi     egoStats					;(= [egoStats MANA] (MaxMana))
;;;
;;; 			pushi    0
;;; 			call     SaveStats,  0	;(SaveStats)
;;;
;;; 			pushi    #dispose
;;; 			pushi    0
;;; 			lofsa    poolBar					;(poolBar dispose:)
;;; 			send     4
;;;
;;; 			pushi    #dispose
;;; 			pushi    0
;;; 			pushi    2
;;; 			pushi    204
;;; 			pushi    0
;;; 			callk    ScriptID,  4				;((ScriptID 204 0) dispose:)
;;; 			send     4
;;;
;;; 			pushi    #dispose
;;; 			pushi    0
;;; 			lofsa    statDial		;(statDial dispose:)
;;; 			send     4
;;;
;;; 			pushi    #dispose
;;; 			pushi    0
;;; 			lag      addToPics		;(addToPics dispose:)
;;; 			send     4
;;;
;;; 			pushi    #drawPic
;;; 			pushi    1
;;; 			pushi    401
;;; 			pushi    #newRoom
;;; 			pushi    1
;;; 			lst      temp2		;temp2 = either 300 (TownOutlook) or 202 (selChar screen)
;;; 			lag      curRoom		;(curRoom drawPic: 401 newRoom: temp2); pic401 is a white screen
;;; 			send     12
;;; 			ret     
;;; 		)
;;; 	)
)
