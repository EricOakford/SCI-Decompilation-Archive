;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use StopWalk)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm1 0
)

(local
	i
	[ripple 3]
	[rippleX 3] = [51 170 288]
	[rippleY 3] = [155 156 158]
)
(instance rm1 of Room
	(properties
		picture 1
		horizon 78
		north 53
		east 83
		west 2
	)
	
	(method (init)
		(LoadMany VIEW 201 202 267 186 0)
		(LoadMany SOUND 78 79 80 11)
		(self keep: 0)
		(self style:
				(switch prevRoomNum
					(north WIPEDOWN)
					(west SCROLLLEFT)
					(east SCROLLRIGHT)
					(south WIPEUP)
					(else 
						(TheMenuBar state: TRUE)
						(if (CheckHowFast 1) 17 else 15)
					)
				)
		)
		(gate illegalBits: 0 ignoreHorizon: setPri: 3 stopUpd:)
		(self setRegions: MOAT)
		(= i 0)
		(while (< i 3)
			((= [ripple i] (Clone Ripple))
				view: 202
				cycleSpeed: 1
				setPri: 3
				x: [rippleX i]
				y: [rippleY i]
				setLoop: 0
				ignoreActors: 1
				sightAngle: 180
				closeRangeDist: 500
				longRangeDist: 500
				description: {ripples}
				init:
				stopUpd:
			)
			(if (>= howFast 1)
				([ripple i] setCycle: Forward)
			)
			(++ i)
		)
		(super init:)
		(if
			(not
				(if (and (ego has: iMagicShield) (ego has: iMagicMirror)) (ego has: iChest))
			)
			(addToPics add: g1 g2 eachElementDo: #init doit:)
		)
		(addToPics add: urn1 urn2 eachElementDo: #init doit:)
		(if (Btst fInCartoon)
			(Bclr fInCartoon)
			(TheMenuBar draw:)
			(StatusLine enable:)
		)
		(switch prevRoomNum
			(200
				(curRoom setScript: exitCastle)
			)
			(west
				(ego posn: 3 (proc0_17 112 (ego y?) 100) init:)
				(gate init:)
				(NormalEgo)
			)
			(east
				(ego posn: 317 (proc0_17 113 (ego y?) 100) init:)
				(gate init:)
				(NormalEgo)
			)
			(else 
				(curRoom setScript: exitCastle)
			)
		)
		(vine1 init:)
		(vine2 init:)
		(vine3 init:)
		(vine4 init:)
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
		)
	)
	
	(method (handleEvent event)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_02bb
			ret     
			jmp      code_04c6
code_02bb:
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Room,  6
			bnt      code_02ca
			ret     
			jmp      code_04c6
code_02ca:
			pushi    1
			lofsa    'open,open/door,gate'
			push    
			callk    Said,  2
			bnt      code_035b
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      86
			gt?     
			bnt      code_02ed
			pushi    2
			pushi    1
			pushi    0
			calle    Print,  4
			jmp      code_04c6
code_02ed:
			pushi    1
			pushi    2
			callb    Btst,  2
			bnt      code_02ff
			pushi    2
			pushi    1
			pushi    1
			calle    Print,  4
			jmp      code_04c6
code_02ff:
			pushi    #has
			pushi    1
			pushi    14
			lag      ego
			send     6
			bnt      code_0350
			pushi    #has
			pushi    1
			pushi    1
			lag      ego
			send     6
			bnt      code_0350
			pushi    #has
			pushi    1
			pushi    16
			lag      ego
			send     6
			bnt      code_0350
			pushi    2
			pushi    1
			pushi    2
			calle    Print,  4
			pushi    #fade
			pushi    0
			pushi    2
			pushi    0
			pushi    23
			callk    ScriptID,  4
			send     4
			pushi    2
			pushi    111
			pushi    3
			callb    SolvePuzzle,  4
			pushi    #setScript
			pushi    1
			lofsa    enterCastle
			push    
			lofsa    gate
			send     6
			jmp      code_04c6
code_0350:
			pushi    2
			pushi    1
			pushi    3
			calle    Print,  4
			jmp      code_04c6
code_035b:
			pushi    1
			lofsa    'close,shut/gate,door'
			push    
			callk    Said,  2
			bnt      code_0371
			pushi    2
			pushi    1
			pushi    4
			calle    Print,  4
			jmp      code_04c6
code_0371:
			pushi    1
			lofsa    'knock/door,gate'
			push    
			callk    Said,  2
			bnt      code_039f
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      86
			lt?     
			bnt      code_0395
			pushi    2
			pushi    1
			pushi    5
			calle    Print,  4
			jmp      code_04c6
code_0395:
			pushi    2
			pushi    1
			pushi    0
			calle    Print,  4
			jmp      code_04c6
code_039f:
			pushi    1
			lofsa    'get,take,use/planter,caldron,planter'
			push    
			callk    Said,  2
			bnt      code_03b5
			pushi    2
			pushi    1
			pushi    6
			calle    Print,  4
			jmp      code_04c6
code_03b5:
			pushi    1
			lofsa    'get,take<in/planter,planter,caldron'
			push    
			callk    Said,  2
			bt       code_03cb
			pushi    1
			lofsa    'hide<in/planter,planter,caldron'
			push    
			callk    Said,  2
			bnt      code_03d6
code_03cb:
			pushi    2
			pushi    1
			pushi    7
			calle    Print,  4
			jmp      code_04c6
code_03d6:
			pushi    1
			lofsa    'look,check,talk,speak,kick,kill,kiss,attack/guard'
			push    
			callk    Said,  2
			bnt      code_03ec
			pushi    2
			pushi    1
			pushi    8
			calle    Print,  4
			jmp      code_04c6
code_03ec:
			pushi    6
			lsp      event
			pushi    101
			pushi    30
			pushi    120
			pushi    48
			pushi    3
			callb    MouseClaimed,  12
			bnt      code_040b
			pushi    2
			pushi    1
			pushi    9
			calle    Print,  4
			jmp      code_04c6
code_040b:
			pushi    1
			lofsa    'look,check/planter,caldron'
			push    
			callk    Said,  2
			bnt      code_0421
			pushi    2
			pushi    1
			pushi    10
			calle    Print,  4
			jmp      code_04c6
code_0421:
			pushi    1
			lofsa    'look,check/ceder,vine,bury'
			push    
			callk    Said,  2
			bnt      code_0437
			pushi    2
			pushi    1
			pushi    11
			calle    Print,  4
			jmp      code_04c6
code_0437:
			pushi    1
			lofsa    'look,check/blossom'
			push    
			callk    Said,  2
			bnt      code_0466
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      112
			lt?     
			bnt      code_045b
			pushi    2
			pushi    1
			pushi    12
			calle    Print,  4
			jmp      code_04c6
code_045b:
			pushi    2
			pushi    1
			pushi    13
			calle    Print,  4
			jmp      code_04c6
code_0466:
			pushi    1
			lofsa    'remove/ring'
			push    
			callk    Said,  2
			bnt      code_04be
			pushi    1
			pushi    1
			callb    Btst,  2
			bnt      code_04b3
			pushi    #has
			pushi    1
			pushi    16
			lag      ego
			send     6
			bnt      code_049c
			pushi    #has
			pushi    1
			pushi    14
			lag      ego
			send     6
			bnt      code_049c
			pushi    #has
			pushi    1
			pushi    1
			lag      ego
			send     6
code_049c:
			not     
			bnt      code_04b3
			pushi    2
			pushi    1
			pushi    14
			calle    Print,  4
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
			jmp      code_04c6
code_04b3:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
			jmp      code_04c6
code_04be:
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Room,  6
code_04c6:
			ret     
		)
	)
)

(instance enterCastle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21)
					number: 78
					priority: 15
					loop: 1
					init:
					play:
				)
				(gate
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					startUpd:
					setMotion: MoveTo 160 25 self
				)
			)
			(1
				((ScriptID 0 21) stop:)
				(gate stopUpd:)
				((ScriptID 0 23) number: 11 loop: -1 play:)
				(if (Btst fInvisible)
					(Print 1 15)
					(Bclr fInvisible)
					(ego view: 0)
					(NormalEgo)
				)
				(ego
					illegalBits: 0
					ignoreActors:
					setLoop: 3
					setMotion: MoveTo (ego x?) 76 self
				)
			)
			(2
				(SolvePuzzle fEndGame 3)
				(HandsOn)
				(curRoom newRoom: 53)
			)
		)
	)
)

(instance exitCastle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gate posn: 160 25 ignoreActors: init: stopUpd:)
				(ego
					setPri: -1
					view: 0
					loop: 2
					illegalBits: 0
					ignoreHorizon:
					posn: 164 76
					setCycle: StopWalk 2
					init:
					setMotion: MoveTo 164 86 self
				)
			)
			(1
				((ScriptID 0 21) number: 79 init: play:)
				(gate setMotion: MoveTo 160 75 self)
			)
			(2
				((ScriptID 0 21) number: 80 play:)
				(gate setMotion: MoveTo 160 80 self)
			)
			(3
				(gate ignoreActors: 0 stopUpd:)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance g1 of RPicView
	(properties
		x 203
		y 85
		description {guard}
		sightAngle 180
		closeRangeDist 300
		longRangeDist 300
		view 186
		signal notUpd
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/guard,man')
				(self doVerb: verbLook)
			)
			((Said 'talk,speak,ask/guard,man')
				(Print 1 16)
			)
			((Said 'give[/*]/guard')
				(Print 1 17)
			)
			((Said 'attack,kick,kill/guard')
				(Print 1 18)
			)
			((Said 'kiss,hug/guard')
				(Print 1 19)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 20)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance g2 of RPicView
	(properties
		x 119
		y 85
		description {guard}
		sightAngle 180
		closeRangeDist 300
		longRangeDist 300
		view 186
		cel 1
		signal notUpd
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 20)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gate of Actor
	(properties
		x 160
		y 80
		view 201
		signal notUpd
		illegalBits $0000
	)
	
	(method (init)
		(self yStep:
			(cond 
				((CheckHowFast 2) 2)
				((CheckHowFast 1) 4)
				((CheckHowFast 0) 7)
			)
		)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/gate,door')
				(self doVerb: verbLook)
			)
			((Said 'enter/castle,gate,door')
				(Print 1 21)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 22)
			)
		)
	)
)

(instance urn1 of RPicView
	(properties
		x 35
		y 103
		description {urn}
		sightAngle 180
		closeRangeDist 300
		longRangeDist 300
		view 267
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((Said 'look,check/planter,planter,planter,caldron')
				(self doVerb: verbLook)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 23)
			)
		)
	)
)

(instance urn2 of RPicView
	(properties
		x 292
		y 103
		description {urn}
		sightAngle 180
		closeRangeDist 300
		longRangeDist 300
		view 267
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 23)
			)
		)
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/moat,water,brook')
				(self doVerb: verbLook)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 1 24)
			)
		)
	)
)

(instance vine1 of NewFeature
	(properties
		x 37
		y 24
		noun '/vine,ivy,bury,ceder'
		nsTop -1
		nsBottom 50
		nsRight 74
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)

(instance vine2 of NewFeature
	(properties
		x 35
		y 56
		noun '/vine,ivy,bury,ceder'
		nsTop 50
		nsLeft 29
		nsBottom 62
		nsRight 41
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)

(instance vine3 of NewFeature
	(properties
		x 282
		y 24
		noun '/vine,ivy,bury,ceder'
		nsLeft 246
		nsBottom 49
		nsRight 319
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)

(instance vine4 of NewFeature
	(properties
		x 284
		y 56
		noun '/vine,ivy,bury,ceder'
		nsTop 50
		nsLeft 279
		nsBottom 62
		nsRight 290
		description {vine}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The castle walls are carpeted with a thick tangle of vines.}
	)
)
