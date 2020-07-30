;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include game.sh)
(use Main)
(use Intrface)
(use Deltaur)
(use SQRoom)
(use SQEgo)
(use DScript)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm61 0
)

(local
	clientX
	clientY
)
(instance rm61 of SQRoom
	(properties
		lookStr {You are standing in the upper level of one of the Deltaur's hallways. 
		You can't help but be impressed with the subtle decorating and color sense shown by the Sariens in their ship design.}
		picture 61
		east 63
	)
	
	(method (init)
		(HandsOff)
		(self setRegions: DELTAUR)
		(LoadMany VIEW 66 161)
		(if (ego has: iCartridge) (LoadMany SOUND 413 5 312))
		(features
			add: bigMach smallerMach upperLevelMachine
			eachElementDo: #init
			doit:
		)
		(standingSarienHead
			init: standingSarien
			setLoop: 8
			view: 66
			cel: 0
		)
		(standingSarien
			init:
			_head: standingSarienHead
			posn: 249 188
			loop: 7
			shootEgo: shootTheEgo1
			blastID: blast1
			level: 2
			regionPathID: 0
			dead: 0
		)
		(switch prevRoomNum
			(62
				(ego posn: 324 108)
				(= currentFloor 2)
			)
			(63
				(= style 2)
				(= currentFloor 1)
				(ego ignoreActors: 0 x: 310)
			)
			(66
				(ego posn: 161 39)
				(= currentFloor 1)
			)
			(else 
				(ego posn: 9 177)
				(= currentFloor 2)
				(if (== (DeltaurRegion egoStatus?) egoSpacesuit)
					(standingSarien shotsFired: 3)
				)
			)
		)
		(if (== currentFloor 1)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							65 72 39 88 319 88 319 189 0 189 0 0
							319 0 319 77 180 77 178 71 134 71 163 40
							146 41 116 72
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 11 171 25 182 251 182 319 125 319 189 0 189 0 171
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 29 168 0 165 0 0 319 0 319 106 247 166 217 175 37 176
						yourself:
					)
			)
		)
		(beamSound number: 5 loop: -1 play:)
		(zap1 setCycle: Forward init:)
		(zap2 setCycle: Forward init:)
		(ego init:)
		(super init: &rest)
		(switch prevRoomNum
			(62
				(self setScript: fromSlantway)
			)
			(66
				(self setScript: fromElevatorRm)
			)
			(63 0)
			(else 
				(self setScript: fromStorageRm)
			)
		)
	)
	
	(method (doit &tmp theControl)
		(cond 
			(script 0)
			(
				(and
					(> (ego y?) 150)
					(== (ego priority?) 2)
					(== currentFloor 2)
				)
				(ego setPri: -1)
			)
			(
				(and
					(< (ego y?) 150)
					(== currentFloor 2)
					(!= (ego priority?) 2)
				)
				(ego setPri: 2)
			)
			((& (= theControl (ego onControl: origin)) cBLUE) (self setScript: toSlantway))
			((& theControl cGREEN) (self setScript: toElevatorRm))
			((& theControl cCYAN) (self setScript: toStorageRm))
		)
		(super doit:)
	)
)

(instance blast1 of Prop
	(properties
		view 479
		loop 15
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(super doVerb: theVerb &rest)
		else
			(Print 61 0)
		)
	)
)

(instance shootTheEgo1 of Script
	(properties)
	
	(method (changeState newState &tmp egoX egoY)
		(switch (= state newState)
			(0
				(client cel: 0 setMotion: 0 view: 415)
				((client _head?) hide:)
				(client setLoop: (proc703_2 client ego))
				(= ticks 18)
			)
			(1
				(if (< (+ (client shotsFired?) 1) 5)
					(client shotsFired: (+ (client shotsFired?) 1))
					(= register 0)
				else
					(= register 1)
					(HandsOff)
				)
				(if (== (client view?) 415)
					(sarienShot play:)
					(client cel: (- (client lastCel:) 2) setCycle: EndLoop self)
				else
					(client view: 415 setMotion: 0 cel: 0 setCycle: EndLoop self)
				)
			)
			(2
				(if register
					(= egoX (ego x?))
					(= egoY (- (ego y?) 35))
				else
					(switch (Random 1 2)
						(1
							(= egoX (- (ego nsLeft?) (Random 1 5)))
						)
						(2
							(= egoX (+ (ego nsRight?) (Random 1 5)))
						)
					)
					(switch (Random 1 2)
						(1
							(= egoY (- (ego nsTop?) (Random 1 5)))
						)
						(2
							(= egoY (+ (ego nsBottom?) (Random 1 5)))
						)
					)
				)
				((client blastID?) init:)
				(if (OneOf (client loop?) 0 2 4)
					((client blastID?) setLoop: 1)
				else
					((client blastID?) setLoop: 2)
				)
				((client blastID?)
					ignoreActors: 1
					view: 479
					posn: egoX egoY
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				((client blastID?) dispose:)
				(= clientX (client x?))
				(= clientY (client y?))
				(if (and register (not (ego script?)))
					(curRoom setScript: (ScriptID 707 1))
				)
				(= seconds 2)
			)
			(4 (self dispose:))
		)
	)
)

(instance fromElevatorRm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 1
					ignoreActors: TRUE
					setMotion: PolyPath 121 73 self
				)
			)
			(1
				(ego ignoreActors: FALSE setPri: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toElevatorRm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 setMotion: PolyPath 147 52 self)
			)
			(1 (curRoom newRoom: 66))
		)
	)
)

(instance fromStorageRm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					loop: 0
					ignoreActors: TRUE
					setMotion: MoveTo 58 177 self
				)
			)
			(1
				(ego ignoreActors: FALSE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toStorageRm of DScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 setMotion: PolyPath 9 177 self)
			)
			(1 (curRoom newRoom: 54))
		)
	)
)

(instance fromSlantway of DScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors: TRUE
					setPri: 2
					setLoop: 5
					setMotion: MoveTo 274 157 self
				)
			)
			(1
				(ego ignoreActors: FALSE setLoop: -1 setPri: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toSlantway of DScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 2
					ignoreActors: TRUE
					setMotion: MoveTo 324 108 self
				)
			)
			(1
				(ego setPri: -1)
				(curRoom newRoom: 62)
			)
		)
	)
)

(instance standingSarien of sarienGuard
	(properties
		description 3718
		approachX 217
		approachY 183
		lookStr 3687
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbTalk verbSmell verbTaste)
	)
	
	(method (doit)
		(if
			(and
				(not guardLocked)
				(not script)
				(== level currentFloor)
				(not (ego script?))
				(not dead)
				(!= (DeltaurRegion egoStatus?) egoWithHelmet)
				(not (curRoom script?))
			)
			(self setScript: shootEgo)
		)
		(super doit: &rest)
	)
)

(instance standingSarienHead of Head
	(properties
		view 66
		loop 8
		cycleSpeed 216
	)
)

(instance zap1 of Prop
	(properties
		x 206
		y 138
		lookStr {The globes crackle with many colors of light and, you suspect, even less healthful manifestations of the electromagnetic spectrum.}
		view 161
		loop 3
		cel 1
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(super doVerb: theVerb &rest)
		else
			(Print 61 0)
		)
	)
)

(instance zap2 of Prop
	(properties
		x 106
		y 138
		lookStr {The globes crackle with many colors of light and, you suspect, even less healthful manifestations of the electromagnetic spectrum.}
		view 161
		loop 3
		cel 1
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(super doVerb: theVerb &rest)
		else
			(Print 61 0)
		)
	)
)

(instance bigMach of RegionFeature
	(properties
		x 157
		y 112
		description {viewer}
		onMeCheck $2000
		level 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (ego has: iCartridge)
					(= lookStr
						{It looks like some kind of cartridge reader. Maybe the cartridge you have will work in it.}
					)
				else
					(= lookStr
						{A tiny little sign tacked on the front reads: Witzend Coil. Disconnect from power supply before servicing.}
					)
				)
				(super doVerb: theVerb theItem &rest)
			)
			(verbUse
				(if (and (== theItem iCartridge) (== currentFloor 2))
					(curRoom setScript: (ScriptID 706 0))
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance smallerMach of RegionFeature
	(properties
		x 84
		y 123
		description {small machine}
		onMeCheck SKIPCHECK
		lookStr {I give up. YOU tell ME what it is.}
		level 2
	)
)

(instance upperLevelMachine of RegionFeature
	(properties
		x 254
		y 23
		description {upper level machine}
		onMeCheck $0800
		lookStr {It looks like some type of experimental orifice.}
		level 1
	)
)

(instance sarienShot of Sound
	(properties
		number 312
	)
)

(instance beamSound of Sound
	(properties)
)
