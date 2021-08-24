;;; Sierra Script 1.0 - (do not remove this comment)
(script# 45)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm45 0
)

(define pX 0)
(define pY 1)

(local
	coordsIndex
	coords = [
		32 0 0 0
		32 83 114 101
		140 59 221 69
		248 59 300 67
		173 71 251 81
		239 85 300 99
		256 141 300 151
		203 153 292 165
		64 136 157 143
		]
	;coords is coordinates: x1 y1, x2 y2
	[bushPosition 2]
	[egoPosition 3]
	local42
	local43
	[local44 4]
	local48
	local49
	local50
	local51
	local52
	local53
	[gobAttackScript 10]
	goblinIndex
	local65 =  10
	local66 = [100 150 200 225 400 625 750 850 950 1050 2 2 2 2 2 2 2 2 2 2]
	lootedGoblin1
	lootedGoblin2
	lootedGoblin3
	lootedGoblin4
	lootedGoblin5
	lootedGoblin6
	lootedGoblin7
	lootedGoblin8
)

;CI: This was manually decompiled.
(procedure (AimToward who what distance &tmp distX distY)
	(= distX (- (who x?) (what x?)))
	(= distY (* (- (who y?) (what y?)) 2)) 

	(return 
		(and 
			(< -110 distX)
			(< distX 100)
			(< -110 distY)
			(< distY 110)
			(u< distance (+ (* distY distY) (* distX distX)))
		)
	)

)

;;;(procedure (AimToward param1 param2 param3 &tmp temp0 temp1)
;;; 	;param1 = client, param2 = ego, param3 = [local66 register] (typically)
;;;
;;; 	(asm
;;; 		pushi    #x
;;; 		pushi    0
;;; 		lap      param1
;;; 		send     4
;;;
;;; 		push    
;;; 		pushi    #x
;;; 		pushi    0
;;; 		lap      param2
;;; 		send     4
;;;
;;; 		sub     
;;; 		sat      temp0		;(= temp0 (param1 x?) - (param2 x?))
;;;
;;;	
;;; 		pushi    2
;;; 		pushi    #y
;;; 		pushi    0
;;; 		lap      param1
;;; 		send     4
;;;	
;;; 		push    
;;; 		pushi    #y
;;; 		pushi    0
;;; 		lap      param2
;;; 		send     4
;;;
;;; 		sub     
;;; 		mul     
;;; 		sat      temp1		;(= temp1 (* (- (param1 y?) (param2 y?)) (param2 y?))) ; temp1 = (y1 - y2) * y2
;;;
;;; 		pushi    65426		;$FF92 into stack/immediate
;;; 		lat      temp0	
;;; 		lt?     				;(if (< temp0 $FF92)
;;; 		bnt      code_0040
;;; 		pprev   
;;; 		ldi      110			;accumulator = 110
;;; 		lt?     
;;;
;;;
;;; code_0040:
;;; 		not     
;;; 		bt       code_0054
;;; 		pushi    65426
;;; 		lat      temp1
;;; 		lt?     				;(if (< temp1 65426) ;aka $FF92
;;; 		bnt      code_0050
;;; 		pprev   
;;; 		ldi      110			;accumulator = 110
;;; 		lt?     
;;;
;;; code_0050:
;;; 		not     
;;; 		bnt      code_0057
;;;
;;; code_0054:
;;; 		ldi      FALSE
;;; 		ret     			;(return FALSE)
;;;
;;; code_0057:
;;; 		lsp      param3
;;; 		lst      temp0
;;; 		lat      temp0
;;; 		mul     
;;; 		push    
;;; 		lst      temp1
;;; 		lat      temp1
;;; 		mul     
;;; 		add     
;;; 		ult?    
;;; 		bnt      code_006f
;;; 		ldi      FALSE
;;; 		ret     			;(return FALSE)
;;; 		jmp      code_0072
;;;
;;; code_006f:
;;; 		ldi      TRUE
;;; 		ret     			;(return TRUE)
;;;
;;; code_0072:
;;; 		ret     
;;; 	)
;;; )

(procedure (CheckGoblinBush x y &tmp ret [temp1 30])
	(= [bushPosition pX] x)
	(= [bushPosition pY] y)
	(= [egoPosition pX] (ego x?))
	(= [egoPosition pY] (ego y?))
	(= coordsIndex [coords 0])
	(= ret 1)
	(while (and coordsIndex ret)
		(= ret
			(BushIsPositive
				[coords coordsIndex]
				[coords (+ coordsIndex 1)]
				[coords (+ coordsIndex 2)]
				[coords (+ coordsIndex 3)]
			)
		)
		(-= coordsIndex 4)
	)
	(return ret)
)

(procedure (BushIsPositive x1 y1 x2 y2)
	(return
		(cond 
			((IsAllSameDirectionA x1 y1 x2 y2) (return TRUE))
			((IsAllSameDirectionB x1 y1 x2 y2) (return TRUE))
			((IsAllSameDirectionC x1 y1 x2 y2) (return TRUE))
			(else (return FALSE))
		)
	)
)

(procedure (IsAllSameDirectionA x1 y1 x2 y2)
	(return
		(cond 
			((and
				(IsPositiveValues x1 y1 [bushPosition pX] [bushPosition pY] [egoPosition pX] [egoPosition pY])
				(IsPositiveValues x2 y1 [bushPosition pX] [bushPosition pY] [egoPosition pX] [egoPosition pY])
				(IsPositiveValues x1 y2 [bushPosition pX] [bushPosition pY] [egoPosition pX] [egoPosition pY])
				(IsPositiveValues x2 y2 [bushPosition pX] [bushPosition pY] [egoPosition pX] [egoPosition pY])
				)
				(return TRUE)
			)
			((or
				(IsPositiveValues x1 y1 [bushPosition pX] [bushPosition pY] [egoPosition pX] [egoPosition pY])
				(IsPositiveValues x2 y1 [bushPosition pX] [bushPosition pY] [egoPosition pX] [egoPosition pY])
				(IsPositiveValues x1 y2 [bushPosition pX] [bushPosition pY] [egoPosition pX] [egoPosition pY])
				(IsPositiveValues x2 y2 [bushPosition pX] [bushPosition pY] [egoPosition pX] [egoPosition pY])
				)
				(return FALSE)
			)
			(else 
				(return TRUE)
			)
		)
	)
)

(procedure (IsAllSameDirectionC x1 y1 x2 y2 &tmp temp0)
	(= temp0 0)
	(if (!=
			(IsPositiveValues [bushPosition pX] [bushPosition pY] x1 y1 x1 y2)
			(IsPositiveValues [egoPosition pX] [egoPosition pY] x1 y1 x1 y2)
		)
		(++ temp0)
	)
	(if (!=
			(IsPositiveValues [bushPosition pX] [bushPosition pY] x1 y1 x2 y1)
			(IsPositiveValues [egoPosition pX] [egoPosition pY] x1 y1 x2 y1)
		)
		(++ temp0)
	)
	(if (< temp0 2) (return TRUE) else (return FALSE))
	
	;CI: NOTE: does any of this code actually get run?
	(if (!= (IsPositiveValues [bushPosition pX] [bushPosition pY] x2 y2 x1 y2)
			(IsPositiveValues [egoPosition pX] [egoPosition pY] x2 y2 x1 y2)
		)
		(++ temp0)
	)
	(if (< temp0 2) (return TRUE) else (return FALSE))

	(if (!=
			(IsPositiveValues [bushPosition pX] [bushPosition pY] x2 y2 x2 y1)
			(IsPositiveValues [egoPosition pX] [egoPosition pY] x2 y2 x2 y1)
		)
		(++ temp0)
	)
	(return (if (< temp0 2) (return TRUE) else (return FALSE)))
)

(procedure (IsPositiveValues xDelta yDelta x1 y1 x2 y2)
	(return
		(if
			(>
				(+
					(* y2 x1)
					(- (* x2 y1))
					(* xDelta (- y1 y2))
					(* yDelta (- x2 x1))
				)
				0
			)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(procedure (IsAllSameDirectionB x1 y1 x2 y2)
	(return
		(cond 
			(
				(and
					(IsPositiveActors x1 y1)
					(IsPositiveActors x2 y1)
					(IsPositiveActors x1 y2)
					(IsPositiveActors x2 y2)
				)
				(return TRUE)
			)
			(
				(or
					(IsPositiveActors x1 y1)
					(IsPositiveActors x2 y1)
					(IsPositiveActors x1 y2)
					(IsPositiveActors x2 y2)
				)
				(return FALSE)
			)
			(else (return TRUE))
		)
	)
)

(procedure (IsPositiveActors xDelta yDelta)
	;CI: not sure exactly what this is supposed to test.
	;, but it exclusively involves the coordinates in coords
	(return
		(if
			
			;tmp = (bx^2 + by^2) - (exbx + eyby) + (yDelta * [eY - bY]) + (xDelta * [eX - bX])
			;if tmp > 0 then TRUE, else FALSE
			
			(>
				(+
					(* [bushPosition pX] [bushPosition pX])
					(* [bushPosition pY] [bushPosition pY])
					(-
						(+
							(* [egoPosition pX] [bushPosition pX])
							(* [egoPosition pY] [bushPosition pY])
						)
					)
					(* yDelta (- [egoPosition pY] [bushPosition pY]))
					(* xDelta (- [egoPosition pX] [bushPosition pX]))
				)
				0
			)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(instance rm45 of Room
	(properties
		picture 45
		style DISSOLVE
		horizon 48
		north 33
		east 51
		south 62
		west 44
	)
	
	(method (init)
		(super init:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(LoadMany VIEW vGoblinDefeated vGoblin)
		(NormalEgo)
		(ego init:)
		(cSound stop:)
		(goblinS play:)
		(if (not numGoblins)
			(Load VIEW vGoblinBush)
			(movingBush init: setScript: bushAttacks)
		)
		(goblin1
			x: (+ 35 (Random 0 25))
			init:
			setScript: goblin1Leaves
		)
		(goblin2
			x: (+ 195 (Random 0 35))
			init:
			setScript: goblin2Leaves
		)
		(goblin5 init: hide: setScript: goblin5Leaves)
		(if (> howFast slow)
			(goblin6 init: hide: setScript: goblin6Leaves)
			(if (> howFast medium)
				(goblin3
					x: (- 310 (Random 0 20))
					init:
					setScript: goblin3Leaves
				)
				(goblin4 init: hide: setScript: goblin4Leaves)
			)
		)
		(switch prevRoomNum
			(33
				(ego posn: 130 50 setMotion: MoveTo 130 60)
			)
			(51
				(ego posn: 318 108 setMotion: MoveTo 305 108)
			)
			(44
				(ego posn: 1 120 setMotion: MoveTo 15 120)
			)
			(vGoblin
				(if monsterHealth
					(ChangeGait MOVE_RUN FALSE)
					(ego posn: 318 108 setMotion: MoveTo 255 108)
				else
					(Load VIEW vEgoDanceBow)
					(ego posn: 185 125 setScript: egoVictorious)
					(switch numGoblins
						(0)
						(1
							(deadGoblin1 setPri: 6 init:)
							(self setScript: firstDeadGoblin)
						)
						(2
							(deadGoblin2 init:)
							(deadGoblin3 init:)
						)
						(3
							(deadGoblin1 setPri: 6 init:)
							(deadGoblin5 init:)
							(deadGoblin6 init:)
						)
						(4
							(deadGoblin1 setPri: 6 init:)
							(deadGoblin2 init:)
							(deadGoblin3 init:)
							(deadGoblin4 init:)
						)
						(5
							(deadGoblin1 setPri: 6 init:)
							(deadGoblin2 init:)
							(deadGoblin3 init:)
							(deadGoblin4 init:)
							(deadGoblin5 init:)
						)
						(6
							(deadGoblin1 setPri: 6 init:)
							(deadGoblin2 init:)
							(deadGoblin3 init:)
							(deadGoblin4 init:)
							(deadGoblin5 init:)
							(deadGoblin6 init:)
						)
						(7
							(deadGoblin1 setPri: 6 init:)
							(deadGoblin2 init:)
							(deadGoblin3 init:)
							(deadGoblin4 init:)
							(deadGoblin5 init:)
							(deadGoblin6 init:)
							(deadGoblin7 setPri: 6 init:)
						)
						(else 
							(deadGoblin1 setPri: 6 init:)
							(deadGoblin2 init:)
							(deadGoblin3 init:)
							(deadGoblin4 init:)
							(deadGoblin5 init:)
							(deadGoblin6 init:)
							(deadGoblin7 setPri: 6 init:)
							(deadGoblin8 init:)
						)
					)
				)
			)
			(else 
				(ego posn: 160 189 setMotion: MoveTo 160 165)
			)
		)
		(if (or (!= prevRoomNum vGoblin) monsterHealth)
			(switch numGoblins
				(0
					(if (Btst fFoundGoblinHideout)
						(= local51 1)
					)
				)
				(1 
					(= local51 1)
					(= local49 1)
				)
				(2
					(= local51 1)
					(= local49 1)
					(= local43 1)
				)
				(3
					(= local51 1)
					(= local49 1)
					(= local43 1)
				)
				(else 
					(= local51 1)
					(= local49 1)
					(= local43 1)
					(= local53 1)
				)
			)
		)
	)
	
	(method (doit)
		(if (ego edgeHit?)
			(Bset fFoundGoblinHideout)
			(goblinS stop:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn45)
		(if (== prevRoomNum vGoblin)
			(= monsterNum FALSE)
		)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							((Said '/bush')
								(HighPrint 45 0)
								;You seem to see movement, but you are not certain what is over there.
							)
							((Said '/boulder')
								(HighPrint 45 1)
								;The rocks look as if they were deliberately piled.
							)
							((Said '/north,south,east,west')
								(HighPrint 45 2)
								;You see forest.
							)
							((Said '/man,creature,monster,goblin')
								(HighPrint 45 3)
								;You see a Goblin, a small but dangerous humanoid.
							)
							((Said '/cave')
								(if (ego inRect: 0 0 106 86)
									(HighPrint 45 4)
									;You see a small cave concealed in the rocks.  The cave is not big enough for you to enter.
									;This is where the Goblins hide.
								else
									(HighPrint 45 5)
									;You can not see a cave.
								)
							)
							((Said '[/!*]')
								(HighPrint 45 6)
								;Something about the bushes here gives you a very bad feeling.
							)
						)
					)
					((Said 'search/goblin,body,man,creature')
						(HighPrint 45 7)
						;You're not close to anything matching that description.
					)
					((Said 'search[/room]')
						(cond 
							((ego inRect: 0 0 106 86)
								(HighPrint 45 8)
								;You find a small cave concealed in the rocks, just big enough for the Goblins to come through.
							)
							((ego inRect: 106 0 187 86)
								(HighPrint 45 9)
								;Nothing here, but you're HOT!
							)
							((ego inRect: 187 0 320 86)
								(HighPrint 45 10)
								;Nothing here, but you're warm.
							)
							((ego inRect: 0 86 106 134)
								(HighPrint 45 11)
								;Nothing here, you're warm.
							)
							((ego inRect: 106 86 187 134)
								(HighPrint 45 10)
								;Nothing here, but you're warm.
							)
							((ego inRect: 187 86 320 134)
								(HighPrint 45 12)
								;Nothing here, you're cold.
							)
							(else
								(HighPrint 45 13)
								;Nothing here, you're frigid.
							)
						)
					)
					((Said 'climb,enter,go/cave,chasm,boulder')
						(HighPrint 45 14)
						;The cave opening is too small and the rocks are too hard for you to be able to enter.
					)
					((Said 'close,close,block/cave,chasm,boulder')
						(HighPrint 45 15)
						;The rocks are too large and heavy to move.
					)
					((Said 'enlarge,break,beat,enlarge/cave,cave,chasm')
						(HighPrint 45 16)
						;You can't do that.  Maybe in the next game.  We're getting behind schedule.
					)
					((Said 'fight[/goblin,creature,man]')
						(HighPrint 45 17)
						;Looks like no one has the stomach to fight you.
					)
					((Said 'move,force/boulder')
						(HighPrint 45 18) ;EO - "rock" should be plural
						;The rock are too large and heavy to move.
					)
				)
			)
		)
	)
)

(instance goblin1 of Actor
	(properties
		y 81
		x 59
		view vGoblin
		loop 2
		illegalBits $0000
	)
	
	(method (doit)
		(if (and local42 local43)
			(= local42 FALSE)
			(self setMotion: MoveTo 146 (self y?))
			(= [gobAttackScript goblinIndex] (Clone goblinAttacks))
			(self setScript: [gobAttackScript goblinIndex] NULL goblinIndex)
			(++ goblinIndex)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) mouseDown)
					(MouseClaimed goblin1 event shiftDown)
				)
				(HighPrint 45 19)
				;It's R2D4!
			)
		)
	)
)

(instance goblin2 of Actor
	(properties
		y 70
		x 200
		view vGoblin
		loop 2
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) mouseDown)
					(MouseClaimed goblin2 event shiftDown)
				)
				(HighPrint 45 20)
				;It's Hollywood, a virtual Goblin!
			)
		)
	)
)

(instance goblin3 of Actor
	(properties
		y 58
		x 296
		view vGoblin
		loop 2
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) mouseDown)
					(MouseClaimed goblin3 event shiftDown)
				)
				(HighPrint 45 21)
				;It's Droopy!
			)
		)
	)
)

(instance goblin4 of Actor
	(properties
		y 69
		x 207
		view vGoblin
		loop 2
		illegalBits $0000
	)
	
	(method (doit)
		(if (and local48 local49)
			(= local48 0)
			(self setMotion: MoveTo 156 69)
			(= [gobAttackScript goblinIndex] (Clone goblinAttacks))
			(self setScript: [gobAttackScript goblinIndex] NULL goblinIndex)
			(++ goblinIndex)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) mouseDown)
					(MouseClaimed goblin4 event shiftDown)
				)
				(HighPrint 45 22)
				;It's Sneaky!
			)
		)
	)
)

(instance goblin5 of Actor
	(properties
		y 173
		x 42
		view vGoblin
		loop 3
		illegalBits $0000
	)
	
	(method (doit)
		(if (and local50 local51)
			(= local50 0)
			(self setMotion: MoveTo 42 125)
			(= [gobAttackScript goblinIndex] (Clone goblinAttacks))
			(self setScript: [gobAttackScript goblinIndex] NULL goblinIndex)
			(++ goblinIndex)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) mouseDown)
					(MouseClaimed goblin5 event shiftDown)
				)
				(HighPrint 45 23)
				;It's Nickaroo, a real Goblin!
			)
		)
	)
)

(instance goblin6 of Actor
	(properties
		y 84
		x 298
		view vGoblin
		loop 3
		illegalBits $0000
	)
	
	(method (doit)
		(if (and local52 local53)
			(= local52 0)
			(self setMotion: MoveTo 200 84)
			(= [gobAttackScript goblinIndex] (Clone goblinAttacks))
			(self setScript: [gobAttackScript goblinIndex] NULL goblinIndex)
			(++ goblinIndex)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) mouseDown)
					(MouseClaimed goblin6 event shiftDown)
				)
				(HighPrint 45 24)
				;It's JohnnyFive!
			)
		)
	)
)

(instance movingBush of Actor
	(properties
		y 94
		x 235
		view vGoblinBush
		loop 4
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(MouseClaimed movingBush event shiftDown)
			)
			(HighPrint 45 25)
			;One strange bush!
		)
		(if (Said 'kill,fight/bush,tree')
			(HighPrint 45 26)
			;A Goblin steps out of the bush.
			(goblinS stop:)
			(= monsterNum vGoblin)
			(curRoom newRoom: vGoblin)
		)
	)
)

(instance deadGoblin1 of View
	(properties
		y 88
		x 79
		view vGoblinDefeated
		cel 1
		priority 6
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(MouseClaimed self event shiftDown)
			)
			(HighPrint 45 27)
			;The only good Goblin is a dead Goblin!
		)
		(if (Said 'search/enemy,goblin,body,man,creature')
			(cond 
				((and (not lootedGoblin1) (<= (ego distanceTo: self) 30))
					(HighPrint 45 28)
					;You find 5 silvers concealed in a pouch.  You take the silvers.
					(= lootedGoblin1 TRUE)
					(GiveMoney -5)
				)
				((and lootedGoblin1 (<= (ego distanceTo: self) 30))
					(HighPrint 45 29)
					;You've already taken his money.
				)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(if (Said 'look/goblin')
			(HighPrint 45 30)
			;You've seen one dead Goblin, you've seen 'em all.
		)
	)
)

(instance deadGoblin2 of View
	(properties
		y 118
		x 235
		view vGoblinDefeated
		cel 3
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(MouseClaimed self event shiftDown)
			)
			(HighPrint 45 31)
			;What's he staring at?
		)
		(if (Said 'search/enemy,goblin,body,man,creature')
			(cond 
				((and (not lootedGoblin2) (<= (ego distanceTo: self) 30))
					(HighPrint 45 32)
					;You find 4 silvers tucked in his tunic.  He has no use for the silvers.  Into your pocket they go.
					(= lootedGoblin2 TRUE)
					(GiveMoney -4)
				)
				((and lootedGoblin2 (<= (ego distanceTo: self) 30))
					(HighPrint 45 29)
					;You've already taken his money.
					)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(if (Said 'look/goblin')
			(HighPrint 45 30)
			;You've seen one dead Goblin, you've seen 'em all.
		)
	)
)

(instance deadGoblin3 of View
	(properties
		y 66
		x 130
		view vGoblinDefeated
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(MouseClaimed self event shiftDown)
			)
			(HighPrint 45 33)
			;Strange place to take a nap.
		)
		(if (Said 'search/enemy,goblin,body,man,creature')
			(cond 
				((and (not lootedGoblin3) (<= (ego distanceTo: self) 30))
					(HighPrint 45 34)
					;The poor slob was the big loser at the poker game.  He didn't even have lunch money.
					(= lootedGoblin3 TRUE)
				)
				((and lootedGoblin3 (<= (ego distanceTo: self) 30))
					(HighPrint 45 35)
					;He didn't have anything.
					)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(if (Said 'look/goblin')
			(HighPrint 45 30)
			;You've seen one dead Goblin, you've seen 'em all.
		)
	)
)

(instance deadGoblin4 of View
	(properties
		y 128
		x 160
		view vGoblinDefeated
		loop 1
		cel 3
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(MouseClaimed self event shiftDown)
			)
			(HighPrint 45 36)
			;He's getting a sun tan?
		)
		(if (Said 'search/enemy,goblin,body,man,creature')
			(cond 
				((and (not lootedGoblin4) (<= (ego distanceTo: self) 30))
					(HighPrint 45 37)
					;It's your lucky day, this was the big winner at last night's poker game.  You take his 35 silvers.
					(= lootedGoblin4 TRUE)
					(GiveMoney -35))
				((and lootedGoblin4 (<= (ego distanceTo: self) 30))
					(HighPrint 45 29)
					;You've already taken his money.
				)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(if (Said 'look/goblin')
			(HighPrint 45 30)
			;You've seen one dead Goblin, you've seen 'em all.
		)
	)
)

(instance deadGoblin5 of View
	(properties
		y 168
		x 292
		view vGoblinDefeated
		cel 2
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(MouseClaimed self event shiftDown)
			)
			(HighPrint 45 38)
			;A good Goblin!
		)
		(if (Said 'search/enemy,goblin,body,man,creature')
			(cond 
				((and (not lootedGoblin5) (<= (ego distanceTo: self) 30))
					(HighPrint 45 39)
					;In this Goblin's left shoe you find 8 silvers. You look around and slip the silvers into your pocket.
					(= lootedGoblin5 TRUE)
					(GiveMoney -8)
				)
				((and lootedGoblin5 (<= (ego distanceTo: self) 30))
					(HighPrint 45 29)
					;You've already taken his money.
				)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(if (Said 'look/goblin')
			(HighPrint 45 30)
			;You've seen one dead Goblin, you've seen 'em all.
		)
	)
)

(instance deadGoblin6 of View
	(properties
		y 162
		x 82
		view vGoblinDefeated
		loop 1
		cel 2
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(MouseClaimed self event shiftDown)
			)
			(HighPrint 45 40)
			;Does anyone know CPR?
		)
		(if (Said 'search/enemy,goblin,body,man,creature')
			(cond 
				((and (not lootedGoblin6) (<= (ego distanceTo: self) 30))
					(HighPrint 45 41)
					;Clutched in his hand are 4 silvers.  You take the money.
					(= lootedGoblin6 TRUE)
					(GiveMoney -4)
				)
				((and lootedGoblin6 (<= (ego distanceTo: self) 30))
					(HighPrint 45 29)
					;You've already taken his money.
				)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(if (Said 'look/goblin')
			(HighPrint 45 30)
			;You've seen one dead Goblin, you've seen 'em all.
		)
	)
)

(instance deadGoblin7 of View
	(properties
		y 75
		x 13
		view vGoblinDefeated
		loop 1
		cel 1
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(MouseClaimed self event shiftDown)
			)
			(HighPrint 45 42)
			;Limp as a dishrag.
		)
		(if (Said 'search/enemy,goblin,body,man,creature')
			(cond 
				((and (not lootedGoblin7) (<= (ego distanceTo: self) 30))
					(HighPrint 45 43)
					;This guy lost all his dough to a loan shark.
					(= lootedGoblin7 TRUE)
				)
				((and lootedGoblin7 (<= (ego distanceTo: self) 30))
					(HighPrint 45 44) ;EO: "You've" should be "You"
					;You've know he's broke.
				)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(if (Said 'look/goblin')
			(HighPrint 45 30)
			;You've seen one dead Goblin, you've seen 'em all.
		)
	)
)

(instance deadGoblin8 of View
	(properties
		y 135
		x 30
		view vGoblinDefeated
		loop 1
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) mouseDown)
				(MouseClaimed self event shiftDown)
			)
			(HighPrint 45 45)
			;He lost.
		)
		(if (Said 'search/enemy,goblin,body,man,creature')
			(cond 
				((and (not lootedGoblin8) (<= (ego distanceTo: self) 30))
					(HighPrint 45 46)
					;The Goblin government lost his paycheck.
					(= lootedGoblin8 TRUE))
				((and lootedGoblin8 (<= (ego distanceTo: self) 30))
					(HighPrint 45 47)
					;He never had any money.
				)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(if (Said 'look/goblin')
			(HighPrint 45 30)
			;You've seen one dead Goblin, you've seen 'em all.
		)
	)
)

(instance goblin1Leaves of Script
	(method (doit)
		(if (and (< state 6) (< (ego y?) 100))
			(self changeState: 6)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(6
				(= local42 TRUE)
				(client
					setCycle: Walk
					setMotion: MoveTo -400 (client y?) self
				)
			)
			(7
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblin2Leaves of Script
	(method (doit)
		(if (and (< state 6) (< (ego y?) 155) (> (ego x?) 135))
			(self changeState: 6)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(6
				(client setCycle: Walk setMotion: MoveTo 262 70 self)
			)
			(7
				(client setMotion: MoveTo 262 80 self)
			)
			(8
				(client setMotion: MoveTo 410 80 self)
			)
			(9
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblin3Leaves of Script
	(method (doit)
		(if (and (< state 6) (< (ego y?) 155) (> (ego x?) 135))
			(self changeState: 6)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(6 (= cycles 8))
			(7
				(client setCycle: Walk setMotion: MoveTo 247 58 self)
			)
			(8
				(client setMotion: MoveTo 247 72 self)
			)
			(9
				(client setMotion: MoveTo 410 72 self)
			)
			(10
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblin4Leaves of Script
	(method (doit)
		(if
			(and
				(< state 6)
				(or (> (ego y?) 120) (< (ego x?) 100))
				(not (cast contains: goblin2))
			)
			(self changeState: 6)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(6
				(client
					show:
					setCycle: Walk
					setMotion: MoveTo 237 69 self
				)
				(= local48 1)
			)
			(7
				(client setMotion: MoveTo 237 53 self)
			)
			(8
				(client setMotion: MoveTo 310 53 self)
			)
			(9
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblin5Leaves of Script
	(method (doit)
		(if
			(and
				(< state 6)
				(or (< (ego y?) 95) (> (ego x?) 220))
				(not (cast contains: goblin3))
			)
			(self changeState: 6)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(6
				(= local50 1)
				(client
					show:
					setCycle: Walk
					setMotion: MoveTo 42 125 self
				)
			)
			(7
				(client setMotion: MoveTo -10 125 self)
			)
			(8
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblin6Leaves of Script
	(method (doit)
		(if
			(and
				(< state 6)
				(> (ego y?) 100)
				(< (ego x?) 150)
				(not (cast contains: goblin3))
			)
			(self changeState: 6)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(6
				(client
					show:
					setCycle: Walk
					setMotion: MoveTo 265 84 self
				)
			)
			(7 (= local52 1) (= seconds 2))
			(8
				(client setMotion: MoveTo 265 66 self)
			)
			(9
				(client setMotion: MoveTo 235 66 self)
			)
			(10
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance goblinAttacks of Script
	(method (doit)
		(if
			(and
				[local66 (+ register local65 local65)]
				(AimToward client ego (+ [local66 register] 5))
			)
			(goblinS stop:)
			(= monsterNum vGoblin)
			(curRoom newRoom: vGoblin)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client illegalBits: cWHITE)
				(= seconds (Random 2 5))
			)
			(1
				(if
					(and
						(not (AimToward client ego [local66 register]))
						(CheckGoblinBush (client x?) (client y?))
					)
					(self changeState: 5)
				else
					(self changeState: 2)
				)
			)
			(2 (= cycles 10))
			(3 (self changeState: 1))
			(5
				(client setMotion: MoveTo (ego x?) (ego y?))
				(if [local66 (+ register local65)]
					(-- [local66 (+ register local65)])
				else
					(= [local66 (+ register local65 local65)] 1)
				)
				(= cycles 12)
			)
			(6
				(client setMotion: 0)
				(self changeState: 1)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'fight[/goblin,creature,man]')
						(HighPrint 45 48)
						;"You'll fight all right."
						(goblinS stop:)
						(= monsterNum vGoblin)
						(curRoom newRoom: vGoblin)
					)
				)
			)
		)
	)
)

(instance bushAttacks of Script
	(method (doit)
		(if
			(and
				(!= state 8)
				(!= state 0)
				(AimToward movingBush ego 5625)
			)
			(self changeState: 8)
		)
		(if
			(and
				(== state 8)
				(not (AimToward movingBush ego 7225))
			)
			(self changeState: 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 8))
			(1
				(if (CheckGoblinBush (client x?) (client y?))
					(self changeState: 5)
				else
					(self changeState: 2)
				)
			)
			(2
				;bush stops walking towards ego.
				(client setLoop: 4 setMotion: 0)
				(= cycles 15)
			)
			(3 (self changeState: 1))
			(5
				(client
					setCycle: Walk
					setLoop: -1
					setMotion: MoveTo (ego x?) (ego y?)
				)
				(= cycles 20)
			)
			(6
				(client setMotion: 0)
				(self changeState: 1)
			)
			(8
				;bush stops walking towards ego.
				(client setLoop: 4 setMotion: 0)
			)
		)
	)
)

(instance egoVictorious of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoDanceBow
					setLoop: 2
					setCel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(1 (ego setCycle: BegLoop self))
			(2
				(NormalEgo)
				(ego loop: 2)
				(HandsOn)
			)
		)
	)
)

(instance firstDeadGoblin of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: FALSE)
				(= cycles 5)
			)
			(1
				(HighPrint 45 49)
				;Wow!  You threw that dead goblin a long way!
				(client setScript: 0)
			)
		)
	)
)

(instance goblinS of Sound
	(properties
		number 76
		priority 3
		loop -1
	)
)
