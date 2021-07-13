;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include game.sh)
(use Main)
(use Intrface)
(use tunisia)
(use GoToSaid)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	agentsBuildingRm 0
)

(instance agentsBuildingRm of Room
	(properties
		picture 80
		horizon 1
		north 84
		east 79
		picAngle 70
	)
	
	(method (init)
		(Load VIEW 280)
		(super init:)
		(self
			setRegions: 310 312
			setFeatures:
				windo
				((Clone windo)
					x: 32
					y: 41
					z: 0
					nsLeft: 18
					nsTop: 26
					nsRight: 46
					nsBottom: 56
					yourself:
				)
				((Clone windo)
					x: 232
					y: 49
					z: 0
					nsLeft: 219
					nsTop: 39
					nsRight: 246
					nsBottom: 59
					yourself:
				)
				building
				((Clone building)
					x: 12
					y: 100
					z: 0
					nsLeft: 0
					nsTop: 85
					nsRight: 24
					nsBottom: 116
					yourself:
				)
				((Clone building)
					x: 76
					y: 161
					z: 0
					nsLeft: 0
					nsTop: 134
					nsRight: 153
					nsBottom: 189
					yourself:
				)
				((Clone building)
					x: 254
					y: 42
					z: 0
					nsLeft: 189
					nsTop: 0
					nsRight: 319
					nsBottom: 84
					yourself:
				)
		)
		(door init:)
		(ego init:)
		(switch prevRoomNum
			(east
				(ego posn: 310 (ego y?) loop: 1 illegalBits: cWHITE)
				(door stopUpd:)
			)
			(else 
				(globalSound
					number: 74
					owner: theGame
					priority: 1
					loop: -1
					play:
				)
				(ego posn: 286 80 loop: 2)
				(if (tunisia bagBound?)
					(Load VIEW 185)
					(van init: setScript: driveAwayScript)
				)
			)
		)
		(if (not (ego has: iTunisiaKey))
			(ego illegalBits: (| cWHITE cBLUE))
		)
	)
	
	(method (dispose)
		(ego illegalBits: cWHITE)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,town,scene]')
				(Print 80 0)
			)
		)
	)
)

(instance driveAwayScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego setMotion: MoveTo 260 118 self)
			)
			(2
				(ego hide:)
				(head init:)
				(= cycles 20)
			)
			(3
				(van setStep: 1 1 setMotion: MoveTo 380 (van y?))
				(= cycles 3)
			)
			(4
				(van setStep: 2 2)
				(= cycles 6)
			)
			(5
				(van setStep: 4 4)
				(= cycles 12)
			)
			(6
				(van setStep: 6 6)
				(= cycles 18)
			)
			(7
				(van setStep: 8 8 setMotion: MoveTo 380 (van y?) self)
			)
			(8
				(curRoom newRoom: 81)
			)
		)
	)
)

(instance door of Prop
	(properties
		y 84
		x 284
		view 280
		signal ignrAct
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(or
					(== (ego onControl: origin) cBLUE)
					(== (ego onControl: origin) cGREEN)
				)
				(not (self script?))
				(ego has: iTunisiaKey)
			)
			(HandsOff)
			(self setScript: doorScript)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event))
					((Said 'look[<at]')
						(Print 80 1)
					)
					((Said 'lock,knock')
						(Print 80 2)
					)
					((GoToIfSaid self event 287 88 0 80 3))
					((Said 'open')
						(if (not (ego has: iTunisiaKey))
							(Print 80 4)
						)
					)
					((and (Said 'unlock') (not (ego has: iTunisiaKey)))
						(Print 80 4)
					)
				)
			)
			((Said 'use/key')
				(if (ego has: iTunisiaKey)
					(HandsOff)
					(ego setMotion: MoveTo 287 88)
				else
					(Print 80 5)
				)
			)
		)
	)
)

(instance doorScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1
				(if (> (ego y?) (client y?))
					(= register 0)
					(ego setMotion: MoveTo (ego x?) 80 self)
				else
					(= register 1)
					(ego setMotion: MoveTo (ego x?) 92 self)
				)
			)
			(2 (client setCycle: BegLoop self))
			(3
				(cond 
					((== register 0)
						(curRoom newRoom: 84)
					)
					((tunisia bagBound?)
						(driveAwayScript cue:)
					)
					(else
						(HandsOn)
						(client stopUpd:)
						(self dispose:)
					)
				)
			)
		)
	)
)

(instance van of Actor
	(properties
		y 126
		x 251
		view 185
		cel 1
	)
)

(instance head of Prop
	(properties
		view 185
		cel 2
	)
	
	(method (init)
		(super init:)
		(self
			x: (+ (van x?) 13)
			y: (- (van y?) 30)
			setPri: (+ (van priority?) 1)
		)
	)
	
	(method (doit)
		(super doit:)
		(self x: (+ (van x?) 13) y: (- (van y?) 30))
	)
)

(instance building of RFeature
	(properties
		y 39
		x 25
		nsBottom 78
		nsRight 51
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 80 6)
					)
				)
			)
		)
	)
)

(instance windo of RFeature
	(properties
		y 10
		x 27
		nsLeft 11
		nsBottom 21
		nsRight 43
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 80 7)
					)
					((Said 'close,open,(climb<(in,through))')
						(BadIdea)
					)
					((Said 'look<(in,through)')
						(Print 80 8)
					)
				)
			)
		)
	)
)
