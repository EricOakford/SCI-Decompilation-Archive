;;; Sierra Script 1.0 - (do not remove this comment)
(script# 48)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm48 0
)

(instance rm48 of Room
	(properties
		picture 48
		east 41
		south 33
		west 47
	)
	
	(method (init)
		(LoadMany VIEW 57 18)
		(LoadMany SOUND 70 95)
		(self style:
			(switch prevRoomNum
				(west WIPERIGHT)
				(south WIPEUP)
				(else  DISSOLVE)
			)
		)
		(super init:)
		(switch prevRoomNum
			(south
				(ego posn: (proc0_17 220 (ego x?) 0) 188 init:)
				(NormalEgo)
			)
			(west
				(ego posn: 3 (proc0_17 188 (ego y?) 127) init:)
				(NormalEgo)
			)
			(else 
				(self setScript: skyDiver)
			)
		)
		(cliff1 init:)
		(cliff2 init:)
		(cliff3 init:)
		(rock2 init:)
		(rock1 init:)
		(bush6 init:)
		(bush5 init:)
		(bush4 init:)
		(bush3 init:)
		(bush2 init:)
		(bush1 init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			((== (ego onControl: origin) cMAGENTA)
				(self setScript: fall)
			)
			((== (ego onControl: origin) cRED)
				(self setScript: fall)
			)
			((== (ego onControl: origin) cBROWN)
				(self setScript: fallNorth)
			)
			((== (ego onControl: origin) cLGREY)
				(self setScript: fall)
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
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'climb,scale,enter,jump[<in,in]/hole')
				(Print 48 0)
			)
			((Said 'climb,scale/cliff')
				(if (> (ego x?) 235)
					(Print 48 1)
				else
					(Print 48 2)
				)
			)
			(
				(or
					(Said 'look,check/hole')
					(MouseClaimed event 152 142 172 150)
					(MouseClaimed event 126 144 152 157)
					(MouseClaimed event 96 150 126 160)
				)
				(if (ego inRect: 88 140 183 167)
					(Print 48 3)
				else
					(Print 48 4)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room]')
						(Print 48 5)
					)
					((Said '/blossom')
						(Print 48 6)
					)
					((Said '/clearing')
						(Print 48 7)
					)
					((Said '/hill,hill')
						(Print 48 8)
					)
				)
			)
		)
	)
)

(instance fallNorth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 57
					illegalBits: 0
					setPri: 9
					loop: 8
					cel: 0
					setMotion: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(ego cycleSpeed: 0 setCycle: EndLoop self)
			)
			(2
				(ego loop: 9 setStep: 2 2 setCycle: EndLoop self)
			)
			(3
				(HandsOn)
				(curRoom newRoom: 73)
			)
		)
	)
)

(instance fall of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 57
					illegalBits: 0
					setPri: 9
					cel: 0
					loop: (if (< (ego x?) 139) 1 else 0)
					setMotion: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(if (Btst fInvisible)
					(Print 48 9)
					(Bclr fInvisible)
					(Bclr fWearingRing)
					(SetItemOwner iMagicRing)
					(theGame changeScore: -3)
				)
				(ego setCycle: EndLoop self)
			)
			(2
				(ego
					setLoop: (if (< (ego x?) 139) 3 else 2)
					cel: 0
					setStep: 2 2
					setCycle: EndLoop self
				)
			)
			(3
				(HandsOn)
				(curRoom newRoom: 73)
			)
		)
	)
)

(instance skyDiver of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 17 loop: 1 init: play:)
				(ego
					init:
					ignoreHorizon: 1
					view: 18
					setPri: 11
					posn: 182 -40
					yStep: 14
					illegalBits: 0
					setCycle: 0
					setMotion: MoveTo 182 133 self
				)
			)
			(1
				((ScriptID 0 21) number: 95 play:)
				(ego
					setLoop: 1
					cycleSpeed: 1
					posn: 181 132
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(ShakeScreen 6)
				(ego setCycle: EndLoop)
				(= seconds 3)
			)
			(3
				(ego setLoop: 2 setCel: 0 posn: 181 132)
				(= cycles 2)
			)
			(4
				((ScriptID 0 21) number: 70 play:)
				(ego cycleSpeed: 2 setCycle: EndLoop self)
			)
			(5
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cliff1 of NewFeature
	(properties
		x 287
		y 123
		noun '/cliff'
		nsTop 57
		nsLeft 255
		nsBottom 189
		nsRight 320
		description {cliff}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The steep face of the cliff is sheer, featureless rock.}
	)
)

(instance cliff2 of NewFeature
	(properties
		x 178
		y 53
		noun '/cliff,wall'
		nsTop 22
		nsLeft 108
		nsBottom 85
		nsRight 249
		description {cliff2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The steep face of the cliff is sheer, featureless rock.}
	)
)

(instance cliff3 of NewFeature
	(properties
		x 52
		y 45
		noun '/cliff,wall'
		nsTop 23
		nsBottom 67
		nsRight 105
		description {cliff3}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The steep face of the cliff is sheer, featureless rock.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 69
		y 151
		noun '/cliff,wall'
		nsTop 141
		nsLeft 42
		nsBottom 161
		nsRight 97
		description {bush1}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A small, commonplace bush grows by the hole in the ground.}
	)
)

(instance rock2 of NewFeature
	(properties
		x 35
		y 120
		noun '/boulder'
		nsTop 116
		nsBottom 124
		nsRight 71
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several small, ordinary rocks are scattered around the part of the forest.}
	)
)

(instance rock1 of NewFeature
	(properties
		x 85
		y 155
		noun '/boulder'
		nsTop 150
		nsLeft 72
		nsBottom 161
		nsRight 98
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several small, ordinary rocks are scattered around the part of the forest.}
	)
)

(instance bush6 of NewFeature
	(properties
		x 41
		y 99
		noun '/bush'
		nsTop 79
		nsBottom 119
		nsRight 83
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Plenty of thick, healthy shrubbery grows here.}
	)
)

(instance bush5 of NewFeature
	(properties
		x 23
		y 67
		noun '/bush'
		nsTop 56
		nsBottom 79
		nsRight 47
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Plenty of thick, healthy shrubbery grows here.}
	)
)

(instance bush4 of NewFeature
	(properties
		x 110
		y 106
		noun '/bush'
		nsTop 93
		nsLeft 82
		nsBottom 119
		nsRight 139
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Plenty of thick, healthy shrubbery grows here.}
	)
)

(instance bush3 of NewFeature
	(properties
		x 84
		y 83
		noun '/bush'
		nsTop 73
		nsLeft 62
		nsBottom 93
		nsRight 107
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Plenty of thick, healthy shrubbery grows here.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 276
		y 182
		noun '/bush'
		nsTop 175
		nsLeft 234
		nsBottom 189
		nsRight 318
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Plenty of thick, healthy shrubbery grows here.}
	)
)
