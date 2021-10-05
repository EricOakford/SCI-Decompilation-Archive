;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include game.sh)
(use Main)
(use Intrface)
(use mwAvoider)
(use TurnLooper)
(use Block)
(use LoadMany)
(use DPath)
(use Wander)
(use Follow)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm11 0
)

(define goatControls $7000) ;(| cLRED cLMAGENTA cYELLOW)

(local
	local0
)
(instance rm11 of Room
	(properties
		picture 11
		horizon 57
		north 22
		east 12
		south 6
		west 10
	)
	
	(method (init)
		(LoadMany SOUND 22 23)
		(LoadMany VIEW 211 21 166)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west SCROLLLEFT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_17 192 (ego x?) 0) (+ horizon 2))
			)
			(south
				(ego posn: (proc0_17 236 (ego x?) 0) 188)
			)
			(west
				(ego
					y: (proc0_17
						189
						(proc0_18 160 (ego y?) 158)
						(+ horizon 2)
					)
				)
			)
			(else 
				(ego
					posn: 317 (proc0_17 182 (proc0_18 138 (ego y?) 120) 99)
				)
			)
		)
		(ego init:)
		(NormalEgo)
		(gate init:)
		(if (Btst fGoatPenOpen)
			(gate ignoreActors: cel: 3)
			(ego illegalBits: cYELLOW)
			(cond 
				(
					(and
						(!= prevRoomNum 10)
						(not deadGoatRoom)
						(not (Btst fOfferedGoatCarrot))
					)
					(Bset fOfferedGoatCarrot)
					(Print 11 0)
				)
				(
					(and
						(not deadGoatRoom)
						(not (Btst fOfferedGoatCarrot))
						(== roomWithLiveGoat 11)
					)
					(Bset fOfferedGoatCarrot)
					(Print 11 0)
				)
			)
		)
		(gate stopUpd:)
		(if
			(and
				(not (Btst fGoatFollows))
				(not deadGoatRoom)
				(not (Btst fOfferedGoatCarrot))
			)
			(Load VIEW 21)
			(Load VIEW 167)
			(goatLpr viewChange: 167)
			(if (and (== roomWithLiveGoat 10) (not (Btst fGoatFriend)))
				(theGoat
					view: 165
					posn: -20 (Random 120 140)
					init:
					looper: goatLpr
					setCycle: Walk
					setScript: changeGoatRoom
				)
				(= local0 (Random (Random 100 140) (Random 160 200)))
			else
				(theGoat
					view: 165
					posn: (Random 20 40) (Random 120 140)
					init:
					looper: goatLpr
					setCycle: Walk
					observeBlocks: pen
					setScript: goatWander
				)
				(= local0 (Random (Random 100 140) (Random 160 200)))
			)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script (script doit:))
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
			(
				(and
					(Btst fGoatFollows)
					(Btst fGoatFriend)
					(not (& (theGoat onControl: origin) goatControls))
					(& (ego onControl: origin) goatControls)
					(or (>= (ego distanceTo: gate) 107) (< (ego y?) 116))
				)
				(Bclr fGoatFollows)
				(self setScript: goatLeaves)
			)
			(
				(and
					(Btst fGoatFollows)
					(not (Btst fGoatFriend))
					(not (theGoat script?))
					(not (& (ego onControl: origin) goatControls))
					(Btst fGoatPenOpen)
					(not (< (ego y?) (theGoat y?)))
				)
				(if (not (ego inRect: 81 157 160 177))
					(theGoat setScript: goatOutaGate)
				else
					(theGoat setMotion: 0)
				)
			)
			((and (Btst fGoatFriend) (& (theGoat onControl: origin) cLRED))
				(Bclr fGoatFriend)
			)
			((and (not (theGoat script?)) (!= (theGoat illegalBits?) -8132))
				(theGoat illegalBits: -8132)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'rub/ring')
				(cond 
					((Btst fInvisible) (Print 11 1))
					((not invisibleRingTimer) (Print 11 2))
					((Btst fWearingRing)
						(Print 11 3)
						(Bset fInvisible)
						(NormalEgo)
						(if (Btst fGoatPenOpen)
							(ego observeControl: cYELLOW)
						)
						(if (and (Btst fGoatFollows) (not (Btst fGoatFriend)))
							(Print 11 4)
							(Bclr fGoatFollows)
							(Bclr fOfferedGoatCarrot)
							(goatLpr viewChange: 167)
							(if (== roomWithLiveGoat 10)
								(theGoat
									posn: -20 (Random 120 140)
									init:
									looper: goatLpr
									setCycle: Walk
									setScript: changeGoatRoom
								)
								(= local0 (Random (Random 100 140) (Random 160 200)))
							else
								(theGoat
									init:
									looper: goatLpr
									setCycle: Walk
									observeBlocks: pen
									setScript: goatWander
								)
								(= local0 (Random (Random 100 140) (Random 160 200)))
							)
						)
					)
					(else (Print 11 5))
				)
			)
			(
			(or (Said 'remove/ring') (Said 'take<off/ring'))
				(cond 
					((== (ego view?) (if (Btst fLittleEgo) 23 else 16))
						(CantDo)
					)
					((not (Btst fWearingRing))
						(Print 11 6)
					)
					(else
						(Print 11 7)
						(Bclr fWearingRing)
						(Bclr fInvisible)
						(NormalEgo)
					)
				)
			)
			(
				(or
					(Said 'look,check/bush')
					(MouseClaimed event 263 151 320 189)
				)
				(Print 11 8)
			)
			(
				(or
					(Said 'look,check/ceder')
					(MouseClaimed event 177 -1 319 51)
					(MouseClaimed event 224 52 319 99)
				)
				(Print 11 9)
			)
			(
				(or
					(Said 'look,check/fence')
					(MouseClaimed event 0 133 86 159)
					(MouseClaimed event 141 129 162 150)
					(MouseClaimed event 0 64 99 83)
					(MouseClaimed event 99 65 129 82)
				)
				(Print 11 10)
			)
			((cast contains: theGoat)
				(cond 
					(deadGoatRoom
						(event claimed: FALSE)
					)
					((or (Said 'talk,speak,say,call') (Said '/hello'))
						(Print 11 11)
					)
					((Said 'capture,pull,pull,get,take/goat')
						(cond 
							((Btst fGoatFollows)
								(Print 11 12)
							)
							((& (ego onControl: origin) goatControls)
								(Print 11 13)
							)
							(else
								(Print 11 14)
							)
						)
					)
					((Said 'feed/goat[/!*]')
						(Print 11 15)
					)
					((Said 'pet,free/goat')
						(Print 11 16)
					)
					((Said 'ride/goat')
						(Print 11 17)
					)
					((or (Said 'feed/carrot') (Said 'give/carrot'))
						(cond 
							((not (ego has: iCarrot))
								(Print 11 18)
							)
							((> (ego distanceTo: theGoat) 32)
								(Print 11 19)
							)
							((Btst fInvisible)
								(Print 11 20)
							)
							(else
								(if (Btst fGoatFollows)
									(Print 11 21)
								else
									(Print 11 22)
								)
								(PutInRoom iCarrot 15)
								(Bclr fGoatFollows)
								(if
								(and (not (Btst fGoatPenOpen)) (& (theGoat onControl: origin) goatControls))
									(Bclr fOfferedGoatCarrot)
									(goatLpr viewChange: 167)
									(theGoat
										view: 165
										looper: goatLpr
										setCycle: Walk
										observeBlocks: pen
										setScript: goatWander
									)
								else
									(theGoat setScript: goatBobs)
								)
								(Bclr fPickedCarrot)
								(theGame changeScore: -2)
							)
						)
					)
					(
						(or
							(Said 'show/carrot')
							(Said 'coax,coax/goat')
							(Said 'show/goat/carrot')
							(Said 'coax,coax/goat/carrot')
						)
						(cond 
							((not (ego has: iCarrot))
								(DontHave)
							)
							((Btst fGoatFollows)
								(Print 11 23)
							)
							(
								(and
									(& (theGoat onControl: origin) goatControls)
									(not (& (ego onControl: origin) goatControls))
								)
								(Print 11 24)
							)
							((Btst fInvisible)
								(Print 11 25)
							)
							((> (ego distanceTo: theGoat) 36)
								(Print 11 26)
							)
							((curRoom script?)
								(CantDo)
							)
							(
								(and
									(not (Btst fGoatFriend))
									(& (ego onControl: origin) goatControls)
									(not (& (theGoat onControl:) goatControls))
								)
								(Print 11 27)
							)
							(else (curRoom setScript: getGoat))
						)
					)
					(
					(or (Said 'shoot/goat') (Said 'kill/goat/shot'))
						(cond 
							(deadGoatRoom
								(Print 11 28)
							)
							((curRoom script?)
								(CantDo)
							)
							((Btst fInvisible)
								(Print 11 29)
							)
							((and (not (Btst fGoatFriend)) (not (& (theGoat onControl:) goatControls)))
								(Print 11 27)
							)
							((or (not (ego has: iPebbles)) (not (ego has: iSlingshot)))
								(Print 11 30)
							)
							(else
								(Print 11 31)
							)
						)
					)
					((or (Said 'stab,kill/goat') (Said 'use,throw/dagger'))
						(cond 
							(deadGoatRoom
								(Print 11 28)
							)
							((curRoom script?)
								(CantDo)
							)
							((and (< (theGoat x?) 0) (not (Btst fGoatFriend)))
								(Print 11 27)
							)
							((and (not (& (ego onControl: origin) goatControls)) (not (Btst fGoatFriend)))
								(Print 11 32)
							)
							((> (ego distanceTo: theGoat) 35)
								(Print 11 33)
							)
							((not (ego has: iDagger))
								(Print 11 34)
							)
							(else
								(curRoom setScript: (ScriptID 600 2))
							)
						)
					)
				)
			)
			((or (Said '/goat') (Said 'show/carrot'))
				(cond 
					((Btst fInvisible)
						(Print 11 35)
					)
					((Btst fGoatFollows)
						(Print 11 23)
					)
					((Btst fOfferedGoatCarrot)
						(Print 11 36)
					)
					(else
						(Print 11 37)
					)
				)
			)
		)
		(cond 
			((Said 'jump/fence')
				(Print 11 38)
			)
			((Said 'climb,scale/fence,gate')
				(if (Btst fGoatPenOpen)
					(Print 11 39)
				else
					(Print 11 40)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,fence]')
						(if (& (ego onControl: origin) goatControls)
							(Print 11 41)
						else
							(Print 11 42)
						)
					)
					((Said '/fence')
						(if (& (ego onControl: origin) goatControls)
							(Print 11 43)
						else
							(Print 11 44)
						)
					)
					((Said '/gate')
						(if (Btst fGoatPenOpen)
							(Print 11 39)
						else
							(Print 11 45)
						)
					)
					((Said '/trough')
						(Print 11 46)
					)
				)
			)
			((Said 'open,open/gate,door')
				(cond 
					((not (ego inRect: 77 126 132 166))
						(CantReach)
					)
					((Btst fGoatPenOpen)
						(Print 11 47)
					)
					(else
						(gate setScript: openIt)
					)
				)
			)
			((Said 'close,shut/gate,door')
				(cond 
					((Btst fGoatFollows)
						(Print 11 48)
					)
					((not (ego inRect: 77 126 132 166))
						(CantReach)
					)
					((not (Btst fGoatPenOpen))
						(Print 11 49)
					)
					(else (gate setScript: closeIt))
				)
			)
			((Said 'attack,kick/goat')
				(if deadGoatRoom
					(Print 11 50)
				else
					(Print 11 51)
				)
			)
		)
	)
)

(instance gate of Prop
	(properties
		x 141
		y 158
		view 211
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((MousedOn self event shiftDown)
				(if (Btst fGoatPenOpen)
					(Print 11 39)
				else
					(Print 11 45)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance goatLpr of TurnLooper
	(properties)
)

(instance pen of Cage
	(properties
		top 85
		left 1
		bottom 156
		right 130
	)
)

(instance goatWander of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
				(and
					(not deadGoatRoom)
					(< (ego distanceTo: theGoat) 30)
				)
				(= cycles 0)
				(theGoat setScript: goatRuns)
			)
			((== (-- local0) 0)
				(theGoat setScript: changeGoatRoom)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGoat setLoop: -1 setMotion: Wander startUpd:)
				(= cycles (Random 14 30))
			)
			(1
				(theGoat setMotion: 0)
				(= cycles (Random 8 17))
			)
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance goatRuns of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGoat
					setAvoider: mwAvoider
					setMotion: MoveTo -20 (theGoat y?) self
				)
				(if (theGoat blocks?) (theGoat ignoreBlocks: pen))
			)
			(1
				(= roomWithLiveGoat 10)
				(theGoat hide: setScript: goatCounter)
				(self dispose:)
			)
		)
	)
)

(instance openIt of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_1014
			pushi    0
			callb    HandsOff,  0
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			bnt      code_100c
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    1
			pushi    0
			callb    Btst,  2
			bnt      code_0ff3
			ldi      23
			jmp      code_0ff5
code_0ff3:
			ldi      16
code_0ff5:
			eq?     
			bnt      code_100c
			pushi    #cue
			pushi    0
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			send     4
			ldi      7
			aTop     cycles
			jmp      code_10e4
code_100c:
			pushi    #cue
			pushi    0
			self     4
			jmp      code_10e4
code_1014:
			dup     
			ldi      1
			eq?     
			bnt      code_105a
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      8192
			and     
			bt       code_103d
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      16384
			and     
			bnt      code_1052
code_103d:
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    99
			pushi    139
			pushSelf
			lag      ego
			send     12
			jmp      code_10e4
code_1052:
			pushi    #cue
			pushi    0
			self     4
			jmp      code_10e4
code_105a:
			dup     
			ldi      2
			eq?     
			bnt      code_107c
			pushi    #number
			pushi    1
			pushi    22
			pushi    93
			pushi    0
			pushi    42
			pushi    0
			pushi    2
			pushi    0
			pushi    21
			callk    ScriptID,  4
			send     14
			ldi      3
			aTop     cycles
			jmp      code_10e4
code_107c:
			dup     
			ldi      3
			eq?     
			bnt      code_10ac
			pushi    3
			lsg      ego
			pushi    100
			pushi    160
			callb    Face,  6
			pushi    0
			callb    RedrawCast,  0
			pushi    #cel
			pushi    1
			pushi    0
			pushi    168
			pushi    1
			pushi    2
			pushi    131
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lofsa    gate
			send     20
			jmp      code_10e4
code_10ac:
			dup     
			ldi      4
			eq?     
			bnt      code_10e4
			pushi    #ignoreActors
			pushi    1
			pushi    1
			pushi    226
			pushi    0
			lofsa    gate
			send     10
			pushi    1
			pushi    fGoatPenOpen
			callb    Bset,  2
			pushi    #illegalBits
			pushi    1
			pushi    49152
			lag      ego
			send     6
			pushi    #illegalBits
			pushi    1
			pushi    49152
			lag      theGoat
			send     6
			pushi    0
			callb    HandsOn,  0
			pushi    #dispose
			pushi    0
			self     4
code_10e4:
			toss    
			ret     
		)
	)
)

(instance closeIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: Avoider)
				(cond 
					((& (ego onControl: origin) cLMAGENTA)
						(if (< (ego x?) 114)
							(ego setMotion: DPath 104 151 114 163 self)
						else
							(ego setMotion: DPath 114 (ego y?) 104 151 114 163 self)
						)
					)
					(
						(and
							(& (ego onControl:) goatControls)
							(& (ego onControl:) cLMAGENTA)
						)
						(ego
							setMotion: MoveTo (- (ego x?) 8) (- (ego y?) 3) self
						)
					)
					(else (self cue:))
				)
			)
			(1
				(ego setAvoider: 0)
				((ScriptID 0 21) number: 23 init: play:)
				(= cycles 3)
			)
			(2
				(Face ego 100 160)
				(RedrawCast)
				(gate cel: 3 cycleSpeed: 2 setCycle: BegLoop self)
			)
			(3
				(gate ignoreActors: 0 stopUpd:)
				(Bclr 23)
				(ego illegalBits: cWHITE)
				(theGoat illegalBits: cWHITE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getGoat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego theGoat)
				(Face theGoat ego)
				(ego view: 21 cel: 0 setCycle: EndLoop)
				(theGoat setMotion: 0 setScript: 0)
				(= cycles 22)
			)
			(1
				((ScriptID 0 21) number: 60 loop: 1 init: play:)
				(if (Btst fTrollPaid)
					(Print 11 52)
				else
					(Print 11 53)
					(SolvePuzzle fOfferedGoatCarrot 5)
				)
				(ego setCycle: BegLoop self)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(if (Btst fGoatPenOpen)
					(ego illegalBits: cYELLOW)
				)
				(if (not (Btst fTrollPaid))
					(theGoat setAvoider: Avoider setMotion: Follow ego 50)
					(Bset fGoatFollows)
				)
				(self dispose:)
			)
		)
	)
)

(instance goatOutaGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 11 54)
				(Face ego theGoat)
				(theGoat
					illegalBits: 0
					ignoreActors: 1
					setPri: 2
					setMotion: MoveTo 80 134 self
				)
			)
			(1
				(theGoat
					illegalBits: 0
					ignoreActors: 1
					setPri: 11
					setMotion: MoveTo 122 165 self
				)
				(if (theGoat blocks?)
					(theGoat ignoreBlocks: pen)
				)
			)
			(2
				(theGoat setMotion: MoveTo 124 170 self)
			)
			(3
				(Face theGoat ego)
				(theGoat
					setPri: -1
					illegalBits: -16384
					ignoreActors: 0
					setMotion: Follow ego 50
				)
				(Bset fGoatFriend)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance changeGoatRoom of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 0) (& (theGoat onControl:) goatControls))
			(= roomWithLiveGoat 11)
		else
			(= roomWithLiveGoat 10)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== roomWithLiveGoat 11)
					(theGoat
						setLoop: -1
						setAvoider: mwAvoider
						setMotion: MoveTo -20 (theGoat y?) self
					)
					(if (theGoat blocks?) (theGoat ignoreBlocks: pen))
				else
					(theGoat
						show:
						setLoop: -1
						setAvoider: 0
						setMotion: MoveTo 30 (theGoat y?) self
					)
				)
			)
			(1
				(if (!= roomWithLiveGoat 11)
					(self changeState: 2)
				else
					(theGoat observeBlocks: pen setScript: goatWander)
					(= local0 (Random (Random 100 140) (Random 160 200)))
					(self dispose:)
				)
			)
			(2
				(= cycles 70)
			)
			(3
				(self changeState: 0)
			)
		)
	)
)

(instance goatCounter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 70))
			(1
				(client setScript: changeGoatRoom)
				(self dispose:)
			)
		)
	)
)

(instance goatBobs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGoat
					setAvoider: 0
					illegalBits: -16322
					setMotion:
						MoveTo
						(Random 10 310)
						(Random (+ (curRoom horizon?) 2) 180)
				)
				(= cycles (Random 20 40))
			)
			(1
				(self changeState: 0)
			)
		)
	)
)

(instance goatLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(Face ego theGoat)
				(theGoat setMotion: MoveTo (theGoat x?) 220 self)
			)
			(1 (Print 11 55) (self cue:))
			(2 (HandsOn) (self dispose:))
		)
	)
)
