;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm40 0
)

(local
	local0
)
(procedure (NoMoreGuesses)
	(Print 40 47)
)

(procedure (localproc_10fc)
	(return
		(if
			(and
				(Btst fGnomeAttention)
				(== (gnome view?) 111)
				(== (gnome loop?) 1)
			)
			((ScriptID 0 21) number: 92 loop: 1 init: play:)
			(++ gnomeNameGuesses)
			(if (or (Btst fGotKey) (Btst fGotBeans) (> gnomeNameGuesses 3))
				(NoMoreGuesses)
			else
				(Printf 40 48 &rest)
				(switch gnomeNameGuesses
					(2
						(Print 40 49)
					)
					(1
						(Print 40 50)
					)
				)
				(if (>= gnomeNameGuesses 3)
					(gnome setScript: failGuess)
				)
			)
			(return TRUE)
		else
			(if (cast contains: gnome)
				(if (Btst fInvisible)
					(Print 40 29)
				else
					(Print 40 30)
				)
			)
			(return FALSE)
		)
	)
)

(instance rm40 of Room
	(properties
		picture 40
		horizon 86
		north 41
		east 39
		south 25
		west 33
	)
	
	(method (init)
		(LoadMany VIEW 111 110 240)
		(LoadMany SOUND 92 74)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(addToPics
			add: goldPile strawPile spinningWheel
			eachElementDo: #init
			doit:
		)
		(= local0 0)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_17 319 (ego x?) 178) (+ horizon 2))
			)
			(south
				(ego posn: (proc0_17 319 (ego x?) 52) 188)
			)
			(west
				(ego posn: 3 (proc0_17 155 (ego y?) 121))
			)
			(east (ego x: 317))
			(else  (ego posn: 3 137))
		)
		(ego init:)
		(NormalEgo)
		(hole init:)
		(bush init:)
		(if (and (not (Btst fGotKey)) (not (Btst fGotBeans)))
			(gnome setScript: gnomeWhittle)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			((and script (!= script gnomeWhittle))
				(script doit:)
			)
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
			((and (& (ego onControl:) cLBLUE) (not local0))
				(= local0 1)
				(Print 40 2)
			)
			((and (not (& (ego onControl:) cLBLUE)) (== local0 1))
				(= local0 0)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((MouseClaimed event 36 158 70 176)
				(Print 40 3)
			)
			((MouseClaimed event 28 0 165 131)
				(Print 40 4)
			)
			((Said 'look,check/boulder')
				(if (< (GetDistance (ego x?) (ego y?) 53 167) 30)
					(Print 40 3)
				else
					(Print 40 4)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,clearing,building,ceder]')
						(Print 40 4)
					)
					((Said '/leaf')
						(Print 40 5)
					)
					((Said '/hay,(heap<hay)')
						(strawPile doVerb: verbLook)
					)
					((Said '/gold,dust,(heap<gold,dust)')
						(goldPile doVerb: verbLook)
					)
					((Said '/wheel,(wheel<spinning)')
						(spinningWheel doVerb: verbLook)
					)
					((Said '/root')
						(Print 40 6)
					)
					((Said '/hole')
						(Print 40 7)
					)
					((Said '/man,gnome')
						(cond 
							((not (cast contains: gnome))
								(Print 40 8)
							)
							((== (gnome view?) 111)
								(if (== (gnome loop?) 2)
									(Print 40 9)
								else
									(Print 40 10)
								)
							)
							(else
								(Print 40 11)
							)
						)
					)
				)
			)
			((or (Said 'kill,attack') (Said 'throw,use/dagger,boulder,shot'))
				(if (cast contains: gnome)
					(Print 40 12)
				else
					(Print 40 13)
				)
			)
			((Said 'get,take>')
				(cond 
					((Said '/hay,(heap<hay)')
						(Print 40 14)
					)
					((Said '/gold,dust,(heap<gold,dust)')
						(Print 40 15)
					)
					((Said '/wheel,(wheel<spinning)')
						(Print 40 16)
					)
				)
			)
			((or (Said 'spin[/hay][/gold<in]') (Said 'spin/gold,wheel') (Said 'make/gold'))
				(Print 40 17)
			)
			((Said 'enter/building[<gnome]')
				(Print 40 2)
			)
		)
	)
	
	(method (notify param1)
		(if (cast contains: gnome)
			(localproc_10fc 40 0)
		else
			(Printf 40 1 param1)
		)
	)
)

(instance gnome of Actor
	(properties
		x 200
		y 122
		noun '/gnome,man'
		view 111
		priority 9
		signal fixPriOn
		illegalBits $0000
	)
	
	(method (doit param1 &tmp temp0)
		(asm
			pushi    #doit
			pushi    0
			&rest    param1
			super    Actor,  4
			pushi    #distanceTo
			pushi    1
			lofsa    gnome
			push    
			lag      ego
			send     6
			sat      temp0
			pushi    1
			pushi    108
			callb    Btst,  2
			not     
			bnt      code_059d
			pushi    1
			pushi    92
			callb    Btst,  2
			not     
			bnt      code_059d
			pushi    1
			pushi    1
			callb    Btst,  2
			not     
			bnt      code_059d
			pushi    #script
			pushi    0
			lofsa    gnome
			send     4
			bnt      code_055b
			pushi    #script
			pushi    0
			lofsa    gnome
			send     4
			push    
			lofsa    gnomeWhittle
			ne?     
code_055b:
			not     
			bnt      code_059d
			lst      temp0
			ldi      40
			lt?     
			bnt      code_059d
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      128
			gt?     
			bnt      code_059d
			pushi    #loop
			pushi    0
			lofsa    gnome
			send     4
			push    
			ldi      2
			eq?     
			bnt      code_059d
			pushi    #start
			pushi    1
			pushi    0
			lofsa    gnomeTalk
			send     6
			pushi    #setScript
			pushi    1
			lofsa    gnomeTalk
			push    
			lofsa    gnome
			send     6
			jmp      code_0602
code_059d:
			pushi    1
			pushi    108
			callb    Btst,  2
			not     
			bnt      code_0602
			pushi    1
			pushi    92
			callb    Btst,  2
			not     
			bnt      code_0602
			pushi    #script
			pushi    0
			lofsa    gnome
			send     4
			bnt      code_05c9
			pushi    #script
			pushi    0
			lofsa    gnome
			send     4
			push    
			lofsa    gnomeWhittle
			ne?     
code_05c9:
			not     
			bnt      code_0602
			lst      temp0
			ldi      70
			gt?     
			bt       code_05dd
			pushi    1
			pushi    1
			callb    Btst,  2
			bnt      code_0602
code_05dd:
			pushi    #loop
			pushi    0
			lofsa    gnome
			send     4
			push    
			ldi      2
			lt?     
			bnt      code_0602
			pushi    #start
			pushi    1
			pushi    4
			lofsa    gnomeTalk
			send     6
			pushi    #setScript
			pushi    1
			lofsa    gnomeTalk
			push    
			lofsa    gnome
			send     6
code_0602:
			ret     
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((or (Said 'kill,attack') (Said 'throw,use/dagger,boulder,shot'))
				(if (cast contains: gnome)
					(Print 40 12)
				else
					(Print 40 13)
				)
			)
			((or (Said 'look,check[/gnome,man,room]') (MousedOn self event shiftDown))
				(event claimed: TRUE)
				(if (== (gnome view?) 111)
					(if (== (gnome loop?) 2)
						(Print 40 9)
					else
						(Print 40 10)
					)
				else
					(Print 40 11)
				)
			)
			((Said 'capture,get,take,take/gnome,man')
				(Print 40 18)
			)
			((Said '/rumpelstilskin')
				(if (Btst fGnomeAttention)
					(if (not (localproc_10fc 40 19))
						(event claimed: FALSE)
					)
				else
					(Print 40 20)
				)
			)
			((Said '/mikel')
				(if (Btst fGnomeAttention)
					(if (or (Btst fGotKey) (Btst fGotBeans))
						(if (!= gnomeNameGuesses 1)
							(++ gnomeNameGuesses)
							(Print 40 21)
						else
							(Print 40 22)
						)
					else
						(NoMoreGuesses)
					)
				else
					(Print 40 20)
				)
			)
			((Said '/nikslitselpmur')
				(if (Btst fGnomeAttention)
					(self setScript: goodGuess)
				else
					(Print 40 20)
				)
			)
			((or (Said 'kiss/gnome,man') (Said 'hug/gnome,man'))
				(Print 40 23)
			)
			((or (Said 'talk,speak') (Said 'say/hello') (Said 'hello'))
				(cond 
					((curRoom script?)
						(CantDo)
					)
					((Btst fInvisible)
						(Print 40 24)
					)
					((and (< (ego distanceTo: gnome) 60) (> (ego y?) 128))
						(if (and (Btst fGnomeAsks) (Btst fGnomeAttention))
							(Print 40 25)
						else
							(gnomeTalk start: 0)
							(gnome setScript: gnomeTalk)
						)
					)
					(else
						(Print 40 26)
					)
				)
			)
			(
			(and (!= (event type?) mouseDown) (Btst fGnomeAttention))
				(theGame handleEvent: event)
				(if (not (event claimed?))
					(cond 
						((and (== (gnome view?) 111) (== (gnome loop?) 1))
							(++ gnomeNameGuesses)
							((ScriptID 0 21) number: 92 loop: 1 init: play:)
							(switch gnomeNameGuesses
								(1
									(Print 40 27)
								)
								(2
									(Print 40 28)
								)
								(3
									(self setScript: failGuess)
								)
								(else
									(NoMoreGuesses)
								)
							)
							(event claimed: TRUE)
						)
						((cast contains: gnome)
							(if (Btst fInvisible)
								(Print 40 29)
							else
								(Print 40 30)
							)
							(event claimed: TRUE)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
			)
		)
	)
)

(instance gnomeWhittle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gnome init: setLoop: 2 cycleSpeed: 1)
				(self cue:)
			)
			(1 (gnome setCycle: EndLoop self))
			(2 (= cycles (Random 1 5)))
			(3 (self changeState: 1))
		)
	)
)

(instance goodGuess of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0ab4
			pushi    0
			callb    HandsOff,  0
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			bnt      code_0aac
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    1
			pushi    0
			callb    Btst,  2
			bnt      code_0a93
			ldi      23
			jmp      code_0a95
code_0a93:
			ldi      16
code_0a95:
			eq?     
			bnt      code_0aac
			pushi    #cue
			pushi    0
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			send     4
			ldi      7
			aTop     cycles
			jmp      code_0ba2
code_0aac:
			pushi    #cue
			pushi    0
			self     4
			jmp      code_0ba2
code_0ab4:
			dup     
			ldi      1
			eq?     
			bnt      code_0b1b
			pushi    #number
			pushi    1
			pushi    74
			pushi    6
			pushi    1
			pushi    1
			pushi    93
			pushi    0
			pushi    42
			pushi    0
			pushi    2
			pushi    0
			pushi    21
			callk    ScriptID,  4
			send     20
			pushi    2
			pushi    40
			pushi    31
			calle    Print,  4
			pushi    2
			pushi    40
			pushi    32
			calle    Print,  4
			pushi    2
			pushi    40
			pushi    33
			calle    Print,  4
			pushi    #distanceTo
			pushi    1
			lofsa    gnome
			push    
			lag      ego
			send     6
			push    
			ldi      15
			lt?     
			bnt      code_0b0a
			pushi    #changeState
			pushi    1
			pushi    2
			self     6
			jmp      code_0ba2
code_0b0a:
			pushi    2
			pushi    40
			pushi    34
			calle    Print,  4
			pushi    #cue
			pushi    0
			self     4
			jmp      code_0ba2
code_0b1b:
			dup     
			ldi      2
			eq?     
			bnt      code_0b2f
			pushi    #setScript
			pushi    2
			lofsa    approachGnome
			push    
			pushSelf
			self     8
			jmp      code_0ba2
code_0b2f:
			dup     
			ldi      3
			eq?     
			bnt      code_0b96
			pushi    #number
			pushi    1
			pushi    105
			pushi    6
			pushi    1
			pushi    1
			pushi    93
			pushi    0
			pushi    42
			pushi    0
			pushi    2
			pushi    0
			pushi    21
			callk    ScriptID,  4
			send     20
			pushi    2
			pushi    107
			pushi    5
			lag      gnomeNameGuesses
			sub     
			push    
			callb    SolvePuzzle,  4
			pushi    2
			pushi    108
			pushi    4
			callb    SolvePuzzle,  4
			pushi    #get
			pushi    1
			pushi    iBeans
			lag      ego
			send     6
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      2
			eq?     
			bnt      code_0b89
			pushi    #setScript
			pushi    2
			lofsa    gnomeWaitLeave
			push    
			pushSelf
			self     8
			jmp      code_0ba2
code_0b89:
			pushi    #setScript
			pushi    2
			lofsa    gnomeLeaves
			push    
			pushSelf
			self     8
			jmp      code_0ba2
code_0b96:
			dup     
			ldi      4
			eq?     
			bnt      code_0ba2
			pushi    #dispose
			pushi    0
			self     4
code_0ba2:
			toss    
			ret     
		)
	)
)

(instance failGuess of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0c2a
			pushi    0
			callb    HandsOff,  0
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			bnt      code_0c22
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    1
			pushi    0
			callb    Btst,  2
			bnt      code_0c09
			ldi      23
			jmp      code_0c0b
code_0c09:
			ldi      16
code_0c0b:
			eq?     
			bnt      code_0c22
			pushi    #cue
			pushi    0
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			send     4
			ldi      7
			aTop     cycles
			jmp      code_0ce5
code_0c22:
			pushi    #cue
			pushi    0
			self     4
			jmp      code_0ce5
code_0c2a:
			dup     
			ldi      1
			eq?     
			bnt      code_0c70
			pushi    2
			pushi    40
			pushi    35
			calle    Print,  4
			pushi    2
			pushi    40
			pushi    36
			calle    Print,  4
			pushi    #distanceTo
			pushi    1
			lofsa    gnome
			push    
			lag      ego
			send     6
			push    
			ldi      15
			lt?     
			bnt      code_0c5f
			pushi    #changeState
			pushi    1
			pushi    2
			self     6
			jmp      code_0ce5
code_0c5f:
			pushi    2
			pushi    40
			pushi    37
			calle    Print,  4
			pushi    #cue
			pushi    0
			self     4
			jmp      code_0ce5
code_0c70:
			dup     
			ldi      2
			eq?     
			bnt      code_0c84
			pushi    #setScript
			pushi    2
			lofsa    approachGnome
			push    
			pushSelf
			self     8
			jmp      code_0ce5
code_0c84:
			dup     
			ldi      3
			eq?     
			bnt      code_0ce5
			pushi    #number
			pushi    1
			pushi    105
			pushi    6
			pushi    1
			pushi    1
			pushi    93
			pushi    0
			pushi    42
			pushi    0
			pushi    2
			pushi    0
			pushi    21
			callk    ScriptID,  4
			send     20
			pushi    2
			pushi    92
			pushi    3
			callb    SolvePuzzle,  4
			pushi    #get
			pushi    1
			pushi    iKey
			lag      ego
			send     6
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      2
			eq?     
			bnt      code_0cd4
			pushi    #setScript
			pushi    1
			lofsa    gnomeWaitLeave
			push    
			lofsa    gnome
			send     6
			jmp      code_0ce0
code_0cd4:
			pushi    #setScript
			pushi    1
			lofsa    gnomeLeaves
			push    
			lofsa    gnome
			send     6
code_0ce0:
			pushi    #dispose
			pushi    0
			self     4
code_0ce5:
			toss    
			ret     
		)
	)
)

(instance approachGnome of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setAvoider: Avoider
					setMotion: MoveTo (gnome x?) (+ (gnome y?) 10) self
				)
			)
			(1
				(ego setAvoider: 0 setHeading: 0)
				(= cycles 5)
			)
			(2
				(Print 40 38)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance gnomeWaitLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 40 39)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 155 136 self
				)
			)
			(1
				(ego setMotion: MoveTo 175 155 self)
			)
			(2
				(Print 40 40)
				(NormalEgo)
				(gnome
					view: 110
					cycleSpeed: 0
					ignoreControl:
					ignoreActors:
					setLoop: -1
					setCel: -1
					setCycle: Walk
					setMotion: MoveTo 158 140 self
				)
			)
			(3
				(gnome setPri: -1 setMotion: MoveTo 100 110 self)
			)
			(4
				(gnome dispose:)
				(HandsOn)
			)
		)
	)
)

(instance gnomeLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gnome
					view: 110
					cycleSpeed: 0
					ignoreControl:
					ignoreActors:
					setLoop: -1
					setCel: -1
					setCycle: Walk
					setMotion: MoveTo 128 122 self
				)
			)
			(1
				(gnome setPri: -1 setMotion: MoveTo 90 108 self)
			)
			(2
				(gnome dispose:)
				(HandsOn)
			)
		)
	)
)

(instance gnomeTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fGnomeAttention)
				(gnome loop: 1 cel: 0)
				(= cycles 2)
			)
			(1
				(gnome loop: 0 setCycle: EndLoop self)
			)
			(2
				(if (Btst fMetGnome)
					(Print 40 41)
					(Print 40 25)
				else
					(Print 40 42)
					(Bset fMetGnome)
				)
				(gnome cel: 0 setCycle: EndLoop self)
			)
			(3
				(if (not (Btst fGnomeAsks))
					(Print 40 43)
					(Bset fGnomeAsks)
				)
				(gnome setLoop: 1)
				(HandsOn)
				(self dispose:)
			)
			(4
				(gnome loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(5
				(Print 40 44)
				(gnome loop: 2 setCycle: Forward)
				(self dispose:)
			)
			(6
				(Print 40 45)
			)
			(7
				(Print 40 46)
			)
		)
	)
)

(instance spinningWheel of PicView
	(properties
		x 242
		y 133
		noun '/wheel'
		nsTop 103
		nsLeft 226
		nsBottom 130
		nsRight 256
		description {spinning wheel}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 500
		shiftClick 1
		view 240
	)
	
	(method (doVerb theVerb)
		(if (== theVerb verbLook)
			(Print 40 51)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance goldPile of PicView
	(properties
		x 224
		y 143
		noun '/gold,dust'
		nsTop 129
		nsLeft 212
		nsBottom 142
		nsRight 238
		description {pile of gold}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 500
		shiftClick 1
		view 240
		cel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 40 52)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance strawPile of PicView
	(properties
		x 265
		y 140
		noun '/heap<hay,hay'
		nsTop 130
		nsLeft 254
		nsBottom 140
		nsRight 277
		description {pile of straw}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 500
		shiftClick 1
		view 240
		cel 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb verbLook)
			(Print 40 53)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance hole of NewFeature
	(properties
		x 122
		y 98
		noun '/hole,door,doorway'
		nsTop 78
		nsLeft 111
		nsBottom 118
		nsRight 133
		description {hole in tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This hole, carved into the side of the rock, is the entrance to the gnome's home.__It's too dark inside to make out any details, and the gnome probably prefers it that way.}
	)
)

(instance bush of NewFeature
	(properties
		x 28
		y 165
		noun '/bush'
		nsTop 153
		nsLeft 7
		nsBottom 178
		nsRight 49
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several nice bushes and plants surround the gnome's home.}
	)
)
