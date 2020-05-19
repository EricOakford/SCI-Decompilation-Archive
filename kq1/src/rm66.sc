;;; Sierra Script 1.0 - (do not remove this comment)
(script# 66)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm66 0
)

(instance rm66 of Room
	(properties
		picture 66
		horizon 103
		north 19
		east 67
		south 67
	)
	
	(method (init)
		(Load VIEW 266)
		(self style:
			(switch prevRoomNum
				(north 0)
				(else  0)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(self setScript: enterMount)
			)
			(else 
				(door init:)
				(ego posn: 303 187 loop: 3 init:)
				(NormalEgo)
				(door stopUpd:)
			)
		)
		(ceiling init:)
		(arch init:)
		(wall1 init:)
		(wall2 init:)
		(wall3 init:)
		(if (Btst fGoatFollows)
			(Print 66 0)
			(Bclr fGoatFollows)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
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
			((& (ego onControl: origin) cYELLOW)
				(self setScript: leaveMount)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check>')
				(if (Said '[<at,around][/room,cave]')
					(if (== prevRoomNum north)
						(Print 66 1)
					else
						(Print 66 2)
					)
				)
			)
			((Said 'climb,scale/wall')
				(Print 66 3)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 136
		y 106
		view 266
	)
	
	(method (handleEvent event)
		(cond 
			((or (Said 'look,check/door')
					(MousedOn self event shiftDown))
					(Print 66 4)
					(event claimed: TRUE)
			)
			((Said 'open,open,unlock,knock/door')
				(Print 66 5)
			)
		)
	)
)

(instance doorCloses of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 21) number: 56 loop: 1 init: play:)
				(door cel: 8 setCycle: BegLoop self)
			)
			(1
				(door stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance leaveMount of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 56 loop: 1 init: play:)
				(door setCycle: EndLoop self)
			)
			(1
				(door stopUpd:)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo (ego x?) 103 self
				)
			)
			(2
				(curRoom newRoom: 19)
			)
		)
	)
)

(instance enterMount of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					posn: 140 113
					loop: 2
					init:
					setMotion: MoveTo 140 123 self
				)
				(door cel: 8 setScript: doorCloses init:)
			)
			(1
				(ego stopUpd:)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance wall1 of NewFeature
	(properties
		x 49
		y 83
		noun 'wall'
		nsTop 34
		nsLeft 19
		nsBottom 132
		nsRight 80
		description {wall}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The walls of this cave are rough and craggy.}
	)
)

(instance wall2 of NewFeature
	(properties
		x 145
		y 67
		noun 'wall'
		nsTop 27
		nsLeft 79
		nsBottom 108
		nsRight 212
		description {wall}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The walls of this cave are rough and craggy.}
	)
)

(instance wall3 of NewFeature
	(properties
		x 249
		y 81
		noun 'wall'
		nsTop 41
		nsLeft 212
		nsBottom 122
		nsRight 287
		description {wall}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The walls of this cave are rough and craggy.}
	)
)

(instance ceiling of NewFeature
	(properties
		x 162
		y 26
		noun 'ceiling'
		nsLeft 46
		nsBottom 53
		nsRight 279
		description {ceiling}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The ceiling is low and rough here.}
	)
)

(instance arch of NewFeature
	(properties
		x 272
		y 170
		noun 'arc'
		nsTop 152
		nsLeft 226
		nsBottom 189
		nsRight 318
		description {arch}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The cave appears to open up into a larger chamber that reaches deep into the mountain.}
	)
)
