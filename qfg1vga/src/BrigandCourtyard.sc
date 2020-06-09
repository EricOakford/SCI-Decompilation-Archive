;;; Sierra Script 1.0 - (do not remove this comment)
(script# 94)
(include game.sh) (include "94.shm")
(use Main)
(use CastFlame)
(use CastOpen)
(use CastDazzle)
(use Door)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm94 0
)

(local
	spellCast
	alreadyCastOpen
	archersAreAsleep
	gateIsOpen
	wire2Known
	wire1Known
	setATrap
	arrowY
	oldEgoLoop
	arrowDirection
	[arrows 28]
	arrowCel
	distX
	i
)
(procedure (CalcArrowLanding param1 param2 param3)
	(= distX 0)
	(if setATrap
		(= arrowCel 6)
	else
		(switch param1
			(0
				(switch param2
					(0
						(if param3
							(= arrowCel 0)
							(= distX 6)
						else
							(= arrowCel 3)
						)
					)
					(1
						(if param3
							(= distX -6)
							(= arrowCel 3)
						else
							(= arrowCel 0)
						)
					)
					(else  (= arrowCel 6))
				)
			)
			(1
				(switch param2
					(0
						(if param3
							(= arrowCel 3)
						else
							(= distX 6)
							(= arrowCel 0)
						)
					)
					(1
						(if param3
							(= arrowCel 0)
						else
							(= distX -6)
							(= arrowCel 3)
						)
					)
					(else  (= arrowCel 6))
				)
			)
			(2
				(switch param2
					(2
						(if param3 (= arrowCel 0) else (= arrowCel 3))
					)
					(3
						(if param3 (= arrowCel 3) else (= arrowCel 0))
					)
					(else  (= arrowCel 6))
				)
			)
			(else 
				(switch param2
					(2
						(if param3 (= arrowCel 3) else (= arrowCel 0))
					)
					(3
						(if param3 (= arrowCel 0) else (= arrowCel 3))
					)
					(else  (= arrowCel 6))
				)
			)
		)
	)
)

(procedure (localproc_087a param1 param2 param3 param4 &tmp temp0 temp1)
	(= temp0
		(localproc_098b
			param1
			param2
			(ego x?)
			(- (ego y?) 30)
			(ego x?)
			(- (ego y?) 15)
		)
	)
	(if
		(==
			temp0
			(= temp1
				(localproc_098b
					param3
					param4
					(ego x?)
					(- (ego y?) 30)
					(ego x?)
					(- (ego y?) 15)
				)
			)
		)
		(return 0)
	)
	(= temp0
		(localproc_098b
			(ego x?)
			(- (ego y?) 30)
			param1
			param2
			param3
			param4
		)
	)
	(return
		(if
			(==
				temp0
				(= temp1
					(localproc_098b
						(ego x?)
						(- (ego y?) 15)
						param1
						param2
						param3
						param4
					)
				)
			)
			(return 0)
		else
			(= arrowY
				(-
					(/
						(+
							(* param4 (ego x?))
							(- (* param4 param1))
							(- (* param2 (ego x?)))
							(* param2 param3)
						)
						(- param3 param1)
					)
					(ego y?)
				)
			)
			(= oldEgoLoop (ego loop?))
			(if (< (ego x?) param1)
				(= arrowDirection 1)
			else
				(= arrowDirection 0)
			)
			(return 1)
		)
	)
)

(procedure (localproc_098b param1 param2 param3 param4 param5 param6)
	(return
		(if
			(>
				(+
					(* param6 param3)
					(- (* param5 param4))
					(* param1 (- param4 param6))
					(* param2 (- param5 param3))
				)
				0
			)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (ArchersAttack)
	(archer1 setScript: archer1Start)
	(archer2 setScript: archer2Start)
	(archer3 setScript: archer3Start)
	(archer4 setScript: archer4Start)
)

(instance rm94 of Room
	(properties
		picture 94
		style DISSOLVE
	)
	
	(method (init)
		(LoadMany VIEW 94 194 590 3 515 536 537)
		(LoadMany SOUND 73 35 81 6 4 9 4)
		(cSound number: 73 loop: -1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 54
						319 189
						278 189
						220 189
						230 183
						288 183
						312 119
						280 94
						180 94
						166 81
						209 72
						259 72
						259 68
						106 68
						112 84
						104 102
						43 102
						7 164
						16 185
						91 185
						97 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						36 149
						135 129
						147 137
						145 152
						56 174
						34 162
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						254 174
						167 149
						167 137
						194 131
						287 150
					yourself:
				)
		)
		(super init:)
		(self
			setFeatures:
				onWire1
				onWire2
				onSacks
				onKegs
				onChasm
				onWindow
				lumber1
				lumber2
		)
		;UPGRADE
;;;		(onWire1 init:)
;;;		(onWire2 init:)
;;;		(onSacks init:)
;;;		(onKegs init:)
;;;		(onWindow init:)
;;;		(lumber1 init:)
;;;		(lumber2 init:)

		(onChasm init:)
		(SolvePuzzle f94EnterFortress 8)
		(NormalEgo)
		(rugTop ignoreActors: TRUE init: stopUpd:)
		(rugBottom ignoreActors: TRUE init: stopUpd:)
		(unSafeBridge ignoreActors: TRUE setPri: 6 init: stopUpd:)
		(safeBridge ignoreActors: TRUE setPri: 6 init: addToPic:)
		(JackCycle init: hide:)
		(archer1 init: hide: setPri: 6 stopUpd:)
		(archer2 init: hide: stopUpd:)
		(archer3 init: hide: stopUpd:)
		(archer4 init: hide: stopUpd:)
		(= archersAreAsleep TRUE)
		(if (or (not (Btst fBrigsUnaware)) (Btst fMinotaurDead))
			(Bset fBeenIn94)
			(= archersAreAsleep FALSE)
			(ArchersAttack)
		)
		(switch prevRoomNum
			(95
				(backDoor cel: 4 setPri: 1 init:)
				(= gateIsOpen TRUE)
				(ego posn: 223 76 init: setMotion: MoveTo 190 76)
			)
			(93
				(backDoor setPri: 1 init:)
				(ego posn: 157 188 init: setMotion: MoveTo 157 172)
			)
			(else 
				(ego posn: 152 170 init:)
				(backDoor setPri: 1 init:)
			)
		)
	)
	
	(method (doit)
		(asm
			lal      setATrap
			not     
			bnt      code_04fc
			pushi    #inRect
			pushi    4
			pushi    95
			pushi    101
			pushi    121
			pushi    116
			lag      ego
			send     12
			bnt      code_04fc
			ldi      1
			sal      setATrap
			pushi    #setScript
			pushi    1
			lofsa    fallBridge
			push    
			lag      ego
			send     6
			jmp      code_05e3
code_04fc:
			lal      setATrap
			not     
			bnt      code_0539
			pushi    238
			pushi    #x
			pushi    0
			lag      ego
			send     4
			le?     
			bnt      code_0512
			pprev   
			ldi      256
			le?     
code_0512:
			not     
			bnt      code_0539
			pushi    105
			pushi    #y
			pushi    0
			lag      ego
			send     4
			le?     
			bnt      code_0539
			pprev   
			ldi      114
			le?     
			bnt      code_0539
			ldi      1
			sal      setATrap
			pushi    #setScript
			pushi    1
			lofsa    fallChasm
			push    
			lag      ego
			send     6
			jmp      code_05e3
code_0539:
			pushi    #inRect
			pushi    4
			pushi    100
			pushi    80
			pushi    180
			pushi    90
			lag      ego
			send     12
			bnt      code_056a
			lal      wire2Known
			not     
			bnt      code_056a
			lal      setATrap
			not     
			bnt      code_056a
			ldi      1
			sal      setATrap
			pushi    #setScript
			pushi    1
			lofsa    egoTripsNorth
			push    
			lag      ego
			send     6
			jmp      code_05e3
code_056a:
			pushi    #inRect
			pushi    4
			pushi    252
			pushi    147
			pushi    310
			pushi    163
			lag      ego
			send     12
			bnt      code_05b2
			lal      wire1Known
			not     
			bnt      code_05b2
			pushi    #script
			pushi    0
			lag      ego
			send     4
			push    
			lofsa    egoTripsSouth
			ne?     
			bnt      code_05b2
			pushi    #script
			pushi    0
			lag      ego
			send     4
			push    
			lofsa    fallChasm
			ne?     
			bnt      code_05b2
			pushi    #setScript
			pushi    1
			lofsa    egoTripsSouth
			push    
			lag      ego
			send     6
			jmp      code_05e3
code_05b2:
			pushi    #inRect
			pushi    4
			pushi    139
			pushi    138
			pushi    187
			pushi    152
			lag      ego
			send     12
			bnt      code_05e3
			lal      setATrap
			not     
			bnt      code_05e3
			ldi      1
			sal      setATrap
			pushi    #setPri
			pushi    1
			pushi    11
			pushi    146
			pushi    1
			lofsa    fallRug
			push    
			lag      ego
			send     12
code_05e3:
			lal      gateIsOpen
			bnt      code_0602
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      2
			and     
			bnt      code_0602
			pushi    #newRoom
			pushi    1
			pushi    95
			lag      curRoom
			send     6
			jmp      code_061f
code_0602:
			pushi    #edgeHit
			pushi    0
			lag      ego
			send     4
			push    
			ldi      3
			eq?     
			bnt      code_061f
			lal      setATrap
			not     
			bnt      code_061f
			pushi    #newRoom
			pushi    1
			pushi    93
			lag      curRoom
			send     6
code_061f:
			pushi    #doit
			pushi    0
			super    Room,  4
			ret     
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_SLEEP
				(EgoDead C_DIE_SLEEP_BRIGANDS C_DIE_SLEEP_BRIGANDS_TITLE)
			)
			(V_DETECT
				(messager say: N_ROOM V_DETECT)
			)
			(V_DAZZLE
				(if (not (rm94 notify: 0))
					(if (CastDazzle)
						(rm94 notify: 2)
					)
				else
					(CastDazzle)
					(rm94 notify: 0)
					(messager say: N_ROOM V_CALM)
					(= archersAreAsleep 0)
				)
			)
			(V_FLAME
				(if (not archersAreAsleep)
					(ego setScript: didDart)
				else
					(rm94 notify: 0)
					(CastFlame 0)
					(= archersAreAsleep FALSE)
				)
			)
			(V_CALM
				(if (rm94 notify: 0)
					(messager say: N_ROOM V_CALM)
				else
					(messager say: N_ROOM V_CALM C_CANTCALM)
				)
			)
			(V_OPEN
				(if alreadyCastOpen
					(messager say: N_ROOM V_OPEN)
				else
					(rm94 notify: 0)
					(rm94 setScript: openMess)
					(= archersAreAsleep FALSE)
					(= alreadyCastOpen TRUE)
					(if (CastOpen ego openMess)
						(JackCycle setScript: jackJumps)
					else
						(openMess changeState: 2)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (notify param1)
		(return
			(switch param1
				(0
					(if (== archersAreAsleep TRUE)
						(= archersAreAsleep FALSE)
						(ArchersAttack)
						(return TRUE)
					else
						(return FALSE)
					)
				)
				(2
					(archer1Start archerStop: 300)
					(archer2Start archerStop: 300)
					(archer3Start archerStop: 300)
					(archer4Start archerStop: 300)
				)
			)
		)
	)
)

(class Archer of Script
	(properties
		archerStop 0
		fequencyA 3
		fequencyB 10
		arrowsShot 0
		maxArrows 30
		minArrows 5
		clientP 0
		shootX 0
		shootY 0
		hideX 0
		hideY 0
		arrowV 0
		arrowL 0
		arrowC 0
		arrowP 0
		startX 0
		startY 0
		A1 0
		B1 0
		X1 0
		Y1 0
		A2 0
		B2 0
		X2 0
		Y2 0
		A3 0
		B3 0
		X3 0
		Y3 0
		A4 0
		B4 0
		X4 0
		Y4 0
		A5 0
		B5 0
		X5 0
		Y5 0
		A6 0
		B6 0
		X6 0
		Y6 0
		begX 0
		begY 0
		endX 0
		endY 0
		archDeltaX 0
		archDeltaY 0
	)
	
	(method (doit)
		(if (and archerStop (not (-- archerStop)))
			(ArchersAttack)
		)
		(cond 
			(
				(and
					(< state 3)
					(or
						(> arrowsShot maxArrows)
						(and archerStop (!= state 10))
						(and
							(< -20 (- startX (ego x?)))
							(< (- startX (ego x?)) 20)
							(!= state 10)
							(< -20 (- startY (ego y?)))
							(< (- startY (ego y?)) 20)
						)
					)
				)
				(self changeState: 10)
			)
			(
			(and (<= arrowsShot maxArrows) (not archerStop))
				(if
					(and
						(not
							(if (< -50 (- startX (ego x?)))
								(< (- startX (ego x?)) 50)
							)
						)
						(== state 11)
					)
					(self changeState: 0)
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					cel: 0
					illegalBits: 0
					setPri: clientP
					ignoreActors:
					show:
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(= cycles (Random fequencyA fequencyB))
			)
			(2
				(if (self archerShoot:)
					(arrowSound play:)
					(self arrowPath:)
					((= arrowP (Prop new:))
						view: arrowV
						setLoop: arrowL
						setCel: arrowC
						setPri: 15
						ignoreActors:
						hide:
						init:
					)
					(client setCycle: EndLoop self)
				else
					(self changeState: 1)
				)
			)
			(3
				(self nextPath:)
				(if
					(and
						(== (ego script?) 0)
						(localproc_087a begX begY endX endY)
					)
					(arrowP hide:)
					(ego setScript: egoHit 0 arrowP)
					(self changeState: 1)
				else
					(arrowP show: posn: endX endY)
				)
				(= ticks 18)
			)
			(4
				(self nextPath:)
				(if
					(and
						(== (ego script?) 0)
						(localproc_087a begX begY endX endY)
					)
					(arrowP hide:)
					(ego setScript: egoHit 0 arrowP)
					(self changeState: 1)
				else
					(arrowP posn: endX endY)
					(= ticks 18)
				)
			)
			(5
				(self nextPath:)
				(if
					(and
						(== (ego script?) 0)
						(localproc_087a begX begY endX endY)
					)
					(arrowP hide:)
					(ego setScript: egoHit 0 arrowP)
					(self changeState: 1)
				else
					(arrowP posn: endX endY)
					(= ticks 18)
				)
			)
			(6
				(self nextPath:)
				(if
					(and
						(== (ego script?) 0)
						(localproc_087a begX begY endX endY)
					)
					(arrowP hide:)
					(ego setScript: egoHit 0 arrowP)
					(self changeState: 1)
				else
					(arrowP posn: endX endY)
					(= ticks 18)
				)
			)
			(7
				(self nextPath:)
				(if
					(and
						(== (ego script?) 0)
						(localproc_087a begX begY endX endY)
					)
					(arrowP hide:)
					(ego setScript: egoHit 0 arrowP)
					(self changeState: 1)
				else
					(arrowP posn: endX endY)
					(= ticks 18)
				)
			)
			(8
				(++ arrowsShot)
				(arrowP dispose:)
				(= ticks 180)
			)
			(9 (self changeState: 1))
			(10 (client setCycle: BegLoop self))
			(11 (client hide:))
		)
	)
	
	(method (archerShoot)
		(return
			(cond 
				(
					(and
						(<= 90 (ego y?))
						(<= (ego y?) 100)
						(< A1 (ego x?))
						(< (ego x?) B1)
					)
					(= endX (- X1 (Random 2 8)))
					(= endY (- Y1 (Random 4 16)))
					(return 1)
				)
				(
					(and
						(<= 100 (ego y?))
						(<= (ego y?) 110)
						(< A2 (ego x?))
						(< (ego x?) B2)
					)
					(= endX (- X2 (Random 2 8)))
					(= endY (- Y2 (Random 4 16)))
					(return 1)
				)
				(
					(and
						(<= 110 (ego y?))
						(<= (ego y?) 120)
						(< A3 (ego x?))
						(< (ego x?) B3)
					)
					(= endX (- X3 (Random 2 8)))
					(= endY (- Y3 (Random 4 16)))
					(return 1)
				)
				(
					(and
						(<= 120 (ego y?))
						(<= (ego y?) 130)
						(< A4 (ego x?))
						(< (ego x?) B4)
					)
					(= endX (- X4 (Random 2 8)))
					(= endY (- Y4 (Random 4 16)))
					(return 1)
				)
				(
					(and
						(<= 130 (ego y?))
						(<= (ego y?) 140)
						(< A5 (ego x?))
						(< (ego x?) B5)
					)
					(= endX (- X5 (Random 2 8)))
					(= endY (- Y5 (Random 4 16)))
					(return 1)
				)
				(
					(and
						(<= 140 (ego y?))
						(<= (ego y?) 150)
						(< A6 (ego x?))
						(< (ego x?) B6)
					)
					(= endX (- X6 (Random 2 8)))
					(= endY (- Y6 (Random 4 16)))
					(return 1)
				)
				(else (return 0))
			)
		)
	)
	
	(method (arrowPath)
		(= archDeltaX (- endX startX))
		(= archDeltaX (/ archDeltaX 5))
		(= archDeltaY (- endY startY))
		(= archDeltaY (/ archDeltaY 5))
		(= endX startX)
		(= endY startY)
		(= begX startX)
		(= begY startY)
	)
	
	(method (nextPath)
		(= endX (+ (= begX endX) archDeltaX))
		(= endY (+ (= begY endY) archDeltaY))
	)
)

(instance rugTop of Prop
	(properties
		x 156
		y 142
		noun N_RUG
		view 94
		loop 1
		priority 8
		signal fixPriOn
	)
)

(instance rugBottom of Prop
	(properties
		x 156
		y 142
		noun N_RUG
		view 94
		loop 1
		cel 1
		priority 9
		signal fixPriOn
	)
)

(instance unSafeBridge of Prop
	(properties
		x 106
		y 115
		noun N_UNSAFEBRIDGE
		view 94
		loop 2
	)
)

(instance safeBridge of Prop
	(properties
		x 247
		y 115
		noun N_SAFEBRIDGE
		view 94
		loop 3
		priority 3
		signal fixPriOn
	)
)

(instance JackCycle of Prop
	(properties
		x 299
		y 168
		noun N_JACKINTHEBOX
		view 94
		loop 5
		priority 15
		signal fixPriOn
	)
)

(instance backDoor of Door
	(properties
		x 224
		y 13
		noun N_BACKDOOR
		view 94
		loop 4
		priority 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(curRoom setScript: enterDiningRoom)
			)
			(V_THIEFKIT
				(messager say: N_BACKDOOR V_LOCKPICK)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance jack1 of View
	(properties
		x 299
		y 168
		noun N_CLOSEDBOX
		view 94
		loop 5
		priority 15
		signal fixPriOn
	)
)

(instance archer1 of Actor
	(properties
		y 116
		noun N_ARCHER1
		view 194
		priority 6
		signal fixPriOn
	)
	
	(method (doit)
		(= i 0)
		(while (<= i 24)
			(if (!= [arrows i] 0)
				(CalcArrowLanding
					[arrows (+ i 1)]
					(ego loop?)
					[arrows (+ i 2)]
				)
				([arrows (+ i 3)]
					setCel: arrowCel
					setPri: (ego priority?)
					posn:
						(+ (ego x?) distX)
						(+ (ego y?) [arrows i])
				)
			)
			(= i (+ i 4))
		)
		(super doit:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_FLAME (ego setScript: didDart))
			(V_DAGGER (ego setScript: didDart))
			(V_ROCK (ego setScript: didDart))
			(V_SWORD (ego setScript: didDart))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance archer2 of Actor
	(properties
		x 319
		y 116
		noun N_ARCHER2
		view 194
		loop 1
	)
	
	(method (doVerb theVerb)
		(archer1 doVerb: theVerb)
	)
)

(instance archer3 of Actor
	(properties
		x 277
		y 83
		noun N_ARCHER3
		view 194
		loop 2
	)
	
	(method (doVerb theVerb)
		(archer1 doVerb: theVerb)
	)
)

(instance archer4 of Actor
	(properties
		x 43
		y 83
		noun N_ARCHER4
		view 194
		loop 3
	)
	
	(method (doVerb theVerb)
		(archer1 doVerb: theVerb)
	)
)

(instance antwerp of Actor
	(properties
		view 590
		loop 7
		priority 15
		signal (| ignrHrz fixPriOn)
	)
	
	(method (init)
		(= nightPalette 1590)
		(PalVary PALVARYTARGET 1590)
		(kernel_128 590)
		(super init:)
	)
)

(instance onSacks of Feature
	(properties
		x 80
		y 71
		noun N_SACKS
		nsTop 53
		nsLeft 54
		nsBottom 89
		nsRight 100
	)
)

(instance onKegs of Feature
	(properties
		x 223
		y 74
		noun N_KEGS
		nsTop 58
		nsLeft 179
		nsBottom 90
		nsRight 268
	)
)

(instance onChasm of Feature
	(properties
		x 158
		y 112
		noun N_CHASM
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init: 25 101 298 101 308 117 10 117
			yourself:
		)
		(super init:)
	)
	
	(method (dispose)
		(onMeCheck dispose:)
		(super dispose:)
	)
)

(instance lumber1 of Feature
	(properties
		x 82
		y 115
		z 30
		noun N_LUMBER1
		nsTop 130
		nsLeft 40
		nsBottom 157
		nsRight 125
	)
)

(instance lumber2 of Feature
	(properties
		x 231
		y 141
		noun N_LUMBER2
		nsTop 121
		nsLeft 195
		nsBottom 162
		nsRight 261
	)
)

(instance onWindow of Feature
	(properties
		x 192
		y 28
		nsTop 18
		nsLeft 174
		nsBottom 39
		nsRight 211
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(cond 
					((ego inRect: 156 65 207 76)
						(messager say: N_WINDOW V_LOOK C_TOOFARFROMWINDOW)
					)
					((not (if (< 59 (ego x?)) (< (ego x?) 240)))
						(if (rm94 notify: 0)
							(archer1 setScript: archer1Start)
							(archer2 setScript: archer2Start)
							(archer3 setScript: archer3Start)
							(archer4 setScript: archer4Start)
							(messager say: N_WINDOW V_LOOK C_BRIGANDSALERTED)
						)
					)
					(else
						(messager say: N_WINDOW V_LOOK C_CANTCALM)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance onWire1 of Feature
	(properties
		x 286
		y 156
		noun N_WIRE
		nsTop 150
		nsLeft 262
		nsBottom 160
		nsRight 307
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(messager say: N_WIRE V_DO)
				(= wire1Known TRUE)
			)
			(V_FLAME
				(messager say: N_WIRE V_DAGGER)
				(= wire1Known TRUE)
			)
			(V_DAGGER
				(messager say: N_WIRE V_DAGGER)
				(= wire1Known TRUE)
			)
			(V_SWORD
				(messager say: N_WIRE V_DAGGER)
				(= wire1Known TRUE)
			)
			(V_LOOK
				(messager say: N_WIRE V_LOOK)
				(= wire1Known V_LOOK)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance onWire2 of Feature
	(properties
		x 137
		y 85
		z 7
		nsTop 73
		nsLeft 100
		nsBottom 83
		nsRight 180
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(messager say: N_WIRE V_LOOK)
				(= wire2Known TRUE)
			)
			(V_FLAME
				(messager say: N_WIRE V_DAGGER)
				(= wire2Known TRUE)
			)
			(V_DAGGER
				(messager say: N_WIRE V_DAGGER)
				(= wire2Known TRUE)
			)
			(V_SWORD
				(messager say: N_WIRE V_DAGGER)
				(= wire2Known TRUE)
			)
			(V_DO
				(messager say: N_WIRE V_DO)
				(= wire2Known TRUE)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance archer1Start of Archer
	(properties
		fequencyA 5
		fequencyB 15
		clientP 6
		shootX 49
		shootY 78
		hideX 16
		hideY 78
		arrowV 194
		arrowL 6
		arrowC 3
		startX 84
		startY 65
		B1 320
		X1 260
		Y1 84
		B2 320
		X2 276
		Y2 97
		B3 320
		X3 294
		Y3 113
		A4 160
		B4 320
		X4 315
		Y4 133
		A5 240
		B5 320
		X5 321
		Y5 141
		A6 1
	)
)

(instance archer2Start of Archer
	(properties
		fequencyA 5
		fequencyB 15
		clientP 4
		shootX 275
		shootY 80
		hideX 296
		hideY 80
		arrowV 194
		arrowL 6
		startX 250
		startY 67
		B1 320
		X1 67
		Y1 77
		B2 320
		X2 47
		Y2 97
		B3 170
		X3 25
		Y3 116
		B4 150
		X4 9
		Y4 132
		B5 145
		X5 5
		Y5 142
		A6 1
	)
)

(instance archer3Start of Archer
	(properties
		fequencyA 5
		fequencyB 15
		clientP 3
		shootX 10
		shootY 106
		hideX -23
		hideY 106
		arrowV 194
		arrowL 6
		arrowC 3
		startX 35
		startY 93
		A1 200
		B1 320
		X1 262
		Y1 85
		A2 200
		B2 320
		X2 273
		Y2 95
		B3 320
		X3 287
		Y3 105
		B4 320
		X4 296
		Y4 111
		B5 320
		X5 306
		Y5 130
		A6 200
		B6 320
		X6 320
		Y6 142
	)
)

(instance archer4Start of Archer
	(properties
		fequencyA 5
		fequencyB 15
		clientP 3
		shootX 313
		shootY 110
		hideX 343
		hideY 110
		arrowV 194
		arrowL 6
		startX 288
		startY 97
		B1 120
		X1 75
		Y1 67
		B2 140
		X2 68
		Y2 86
		B3 200
		X3 36
		Y3 104
		B4 320
		X4 23
		Y4 120
		B5 320
		X5 16
		Y5 123
		B6 320
		X6 7
		Y6 135
	)
)

(instance arrowSound of Sound
	(properties
		number 31
	)
)

(instance fallSound of Sound
	(properties
		number 9
	)
)

(instance JackSound of Sound
	(properties
		number 81
	)
)

(instance lockSound of Sound
	(properties
		number 35
	)
)

(instance antwerpSound of Sound
	(properties
		number 4
	)
)

(instance egoHit of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(register show:)
				(cond 
					((ego has: iChainmail)
						(TakeDamage 15)
					)
					((ego has: iLeather)
						(TakeDamage 30)
					)
					(else
						(TakeDamage 45)
					)
				)
				(cond 
					((== [arrows 0] 0)
						(= [arrows 0] arrowY)
						(= [arrows 1] oldEgoLoop)
						(= [arrows 2] arrowDirection)
						(= [arrows 3] register)
					)
					((== [arrows 4] 0)
						(= [arrows 4] arrowY)
						(= [arrows 5] oldEgoLoop)
						(= [arrows 6] arrowDirection)
						(= [arrows 7] register)
					)
					((== [arrows 8] 0)
						(= [arrows 8] arrowY)
						(= [arrows 9] oldEgoLoop)
						(= [arrows 10] arrowDirection)
						(= [arrows 11] register)
					)
					((== [arrows 12] 0)
						(= [arrows 12] arrowY)
						(= [arrows 13] oldEgoLoop)
						(= [arrows 14] arrowDirection)
						(= [arrows 15] register)
					)
					((== [arrows 16] 0)
						(= [arrows 16] arrowY)
						(= [arrows 17] oldEgoLoop)
						(= [arrows 18] arrowDirection)
						(= [arrows 19] register)
					)
					((== [arrows 20] 0)
						(= [arrows 20] arrowY)
						(= [arrows 21] oldEgoLoop)
						(= [arrows 22] arrowDirection)
						(= [arrows 23] register)
					)
					((== [arrows 24] 0)
						(= [arrows 24] arrowY)
						(= [arrows 25] oldEgoLoop)
						(= [arrows 26] arrowDirection)
						(= [arrows 27] register)
					)
					(else (register dispose:))
				)
				(if (<= [egoStats HEALTH] 0)
					(= cycles 3)
				else
					(self dispose:)
				)
			)
			(1
				(EgoDead C_DIE_BRIGAND_ARCHERS C_DIE_BRIGAND_ARCHERS_TITLE)
			)
		)
	)
)

(instance didDart of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(archer1 setCycle: EndLoop)
				(archer2 setCycle: EndLoop)
				(archer3 setCycle: EndLoop)
				(archer4 setCycle: EndLoop self)
			)
			(1
				(EgoDead C_DIE_FIGHT_ARCHERS C_DIE_FIGHT_ARCHERS_TITLE)
			)
		)
	)
)

(instance openMess of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(messager say: N_ROOM NULL C_OPENHASP)
				(lockSound play:)
				(= ticks 60)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance fallBridge of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setLoop: 0)
				(= ticks 6)
			)
			(1
				(lockSound play:)
				(ego
					view: 3
					setLoop: 0
					setCel: 0
					illegalBits: 0
					setPri: 8
					setMotion: 0
					setCycle: 0
					posn: 113 110
				)
				(unSafeBridge setCel: 1)
				(= ticks 3)
			)
			(2
				(ego setCel: 1 posn: 114 111)
				(= ticks 3)
			)
			(3
				(ego setCel: 2 posn: 120 114)
				(= ticks 3)
			)
			(4
				(fallSound play:)
				(ego setCel: 3 setPri: 7 posn: 127 118)
				(= ticks 3)
			)
			(5
				(ego setCel: 4 posn: 130 120)
				(= ticks 3)
			)
			(6
				(ego setCel: 5 posn: 133 120)
				(= ticks 3)
			)
			(7
				(ego setCel: 6 posn: 136 120)
				(= ticks 3)
			)
			(8
				(ego hide:)
				(unSafeBridge setCel: 0)
				(if (rm94 notify: 0)
					(archer1 setScript: archer1Start)
					(archer2 setScript: archer2Start)
					(archer3 setScript: archer3Start)
					(archer4 setScript: archer4Start)
				)
				(= ticks 120)
			)
			(9
				(EgoDead C_DIE_BRIDGE_TRAP C_DIE_BRIDGE_TRAP_TITLE)
				(self dispose:)
			)
		)
	)
)

(instance fallChasm of Script
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 setCycle: 0)
				(if (== (ego loop?) 3) (ego setLoop: 1))
				(= ticks 12)
			)
			(1
				(cond 
					((and (< 226 (ego x?)) (< (ego x?) 247)) (ego posn: 226 109))
					((and (< 246 (ego x?)) (< (ego x?) 273)) (ego posn: 273 109))
					(else (ego posn: (ego x?) 109))
				)
				(ego setLoop: 2)
				(= ticks 12)
			)
			(2
				(ego view: 537 setLoop: 0 cel: 0 setPri: 7)
				(= ticks 60)
			)
			(3
				(ego setCel: 1)
				(= ticks 60)
			)
			(4
				(ego setCel: 0)
				(= ticks 60)
			)
			(5
				(ego setCel: 2)
				(= ticks 60)
			)
			(6
				(ego setLoop: 0 posn: (ego x?) 114)
				(= ticks 2)
			)
			(7
				(ego setLoop: 0 posn: (ego x?) 120)
				(= ticks 2)
			)
			(8
				(ego setLoop: 0 posn: (ego x?) 127)
				(= ticks 2)
			)
			(9
				(ego setLoop: 0 posn: (ego x?) 135)
				(= ticks 2)
			)
			(10
				(ego setLoop: 0 posn: (ego x?) 144)
				(= ticks 2)
			)
			(11
				(ego setLoop: 0 posn: (ego x?) 154)
				(= ticks 1)
			)
			(12
				(ego setCel: 3)
				(= ticks 1)
			)
			(13
				(ego hide:)
				(self cue:)
			)
			(14
				(if (rm94 notify: 0)
					(archer1 setScript: archer1Start)
					(archer2 setScript: archer2Start)
					(archer3 setScript: archer3Start)
					(archer4 setScript: archer4Start)
				)
				(= ticks 120)
			)
			(15
				(EgoDead C_DIE_FALL_PIT C_DIE_FALL_PIT_TITLE)
				(self dispose:)
			)
		)
	)
)

(instance egoTripsNorth of Script
	
	(method (doit)
		(super doit: &rest)
		(if (and (>= state 4) (== (antwerp cel?) 0))
			(antwerpSound play:)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState &tmp egoX_2)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((> (ego x?) 153) (= egoX_2 153))
					((< (ego x?) 131) (= egoX_2 131))
					(else (= egoX_2 (ego x?)))
				)
				(ego
					ignoreActors:
					view: 536
					setLoop: 0
					cel: 0
					setPri: 3
					illegalBits: 0
					posn: egoX_2 80
					setCycle: EndLoop
					setMotion: MoveTo egoX_2 80 self
				)
			)
			(1
				(fallSound play:)
				(antwerp
					setLoop: 0
					setPri: 15
					ignoreActors:
					ignoreHorizon:
					x: -20
					y: -26
					xStep: 12
					yStep: 8
					init:
					setMotion: MoveTo 53 26 self
				)
			)
			(2
				(antwerp
					moveSpeed: 3
					cycleSpeed: 3
					setCycle: Forward
					setMotion: MoveTo (- (ego x?) 30) -40 self
				)
			)
			(3
				(antwerp setMotion: MoveTo (ego x?) (ego y?) self)
			)
			(4
				(fallSound stop:)
				(antwerp moveSpeed: 5 cycleSpeed: 5)
				(self cue:)
			)
			(5
				(if (rm94 notify: 0)
					(archer1 setScript: archer1Start)
					(archer2 setScript: archer2Start)
					(archer3 setScript: archer3Start)
					(archer4 setScript: archer4Start)
				)
				(= ticks 90)
			)
			(6
				(antwerp moveSpeed: 8 cycleSpeed: 8)
				(= ticks 90)
			)
			(7
				(antwerp moveSpeed: 12 cycleSpeed: 12)
				(= ticks 90)
			)
			(8
				(EgoDead C_DIE_TRIPROPE C_DIE_TRIPROPE_TITLE)
				(self dispose:)
			)
		)
	)
)

(instance egoTripsSouth of Script
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= wire1Known TRUE)
				(ego
					view: 536
					setLoop: 0
					cel: 0
					posn: 290 151
					illegalBits: 0
					setCycle: EndLoop
					setMotion: MoveTo 288 (- (ego y?) 12) self
				)
				(if (not alreadyCastOpen)
					(JackCycle setScript: jackJumps)
				)
			)
			(1
				(= ticks 60)
			)
			(2
				(ego view: 515 setLoop: 1 setCel: 4 setCycle: BegLoop self)
			)
			(3
				(NormalEgo)
				(ego loop: 3)
				(= ticks 18)
			)
			(4
				(NormalEgo)
				(ego loop: 1)
				(= ticks 18)
			)
			(5
				(NormalEgo)
				(ego loop: 2)
				(= ticks 18)
			)
			(6
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fallRug of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 setCycle: 0)
				(if (== (ego loop?) 3)
					(ego setLoop: 1)
				)
				(= ticks 10)
			)
			(1
				(ego setLoop: 2)
				(= ticks 12)
			)
			(2
				(ego view: 537 setLoop: 0 cel: 0 setPri: 12 posn: 158 147)
				(rugBottom dispose:)
				(rugTop setLoop: 0 setPri: 11)
				(= ticks 60)
			)
			(3
				(ego setCel: 1)
				(= ticks 60)
			)
			(4
				(ego setCel: 0)
				(= ticks 60)
			)
			(5
				(rugTop setCel: 1)
				(ego setCel: 2)
				(= ticks 60)
			)
			(6
				(fallSound play:)
				(ego setPri: 8 posn: (ego x?) (+ (ego y?) 5))
				(rugTop dispose:)
				(= ticks 10)
			)
			(7
				(ego posn: (ego x?) (+ (ego y?) 7))
				(= ticks 5)
			)
			(8
				(ego posn: (ego x?) (+ (ego y?) 10))
				(= ticks 5)
			)
			(9
				(ego posn: (ego x?) (+ (ego y?) 14))
				(= ticks 5)
			)
			(10
				(ego posn: (ego x?) (+ (ego y?) 19))
				(= ticks 5)
			)
			(11
				(if (rm94 notify: 0)
					(archer1 setScript: archer1Start)
					(archer2 setScript: archer2Start)
					(archer3 setScript: archer3Start)
					(archer4 setScript: archer4Start)
				)
				(= ticks 120)
			)
			(12
				(EgoDead C_DIE_RUG_TRAP C_DIE_RUG_TRAP_TITLE)
				(self dispose:)
			)
		)
	)
)

(instance jackJumps of Script

	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(jack1 init: setPri: 13)
				(= alreadyCastOpen TRUE)
				(= ticks 30)
			)
			(2
				(jack1 dispose:)
				(JackCycle init: show: setCycle: CycleTo 1 1 self)
				(JackSound init: play:)
				(if (rm94 notify: 0)
					(archer1 setScript: archer1Start)
					(archer2 setScript: archer2Start)
					(archer3 setScript: archer3Start)
					(archer4 setScript: archer4Start)
				)
			)
			(3
				(JackCycle setCel: 2 setCycle: EndLoop self)
				(JackSound play:)
			)
			(4
				(JackCycle cycleSpeed: 2 setLoop: 6 setCycle: CycleTo 1 1 self)
				(JackSound play:)
			)
			(5
				(JackCycle setCycle: EndLoop self)
				(JackSound play:)
			)
			(6
				(JackCycle cycleSpeed: 4 setCycle: CycleTo 1 1 self)
				(JackSound play:)
			)
			(7
				(JackCycle setCycle: EndLoop self)
				(JackSound play:)
			)
			(8
				(JackCycle cycleSpeed: 6 setCycle: CycleTo 1 1 self)
				(JackSound play:)
			)
			(9
				(JackCycle setCycle: EndLoop self)
				(JackSound play:)
			)
			(10
				(JackCycle cycleSpeed: 9 setCycle: CycleTo 1 1 self)
				(JackSound play:)
			)
			(11
				(JackCycle setCycle: EndLoop self)
				(JackSound play:)
			)
			(12
				(JackCycle cycleSpeed: 12 setCycle: CycleTo 1 1 self)
				(JackSound play:)
			)
			(13
				(JackCycle setCycle: EndLoop self)
				(JackSound play:)
			)
			(14
				(JackCycle cycleSpeed: 15 setCycle: EndLoop self)
				(JackSound play:)
			)
			(15
				(JackSound dispose:)
				(self dispose:)
			)
		)
	)
)

(instance openGate of Script
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(backDoor cycleSpeed: 2 setCycle: EndLoop self)
			)
			(1
				(= gateIsOpen TRUE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance closeGate of Script
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(backDoor cycleSpeed: 2 setCycle: BegLoop self)
			)
			(1
				(= gateIsOpen FALSE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterDiningRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 234 69 self)
			)
			(1
				(backDoor setCycle: EndLoop self)
				(ego setMotion: MoveTo 235 61 self)
			)
			(2
				(HandsOn)
				(curRoom newRoom: 95)
			)
		)
	)
)
