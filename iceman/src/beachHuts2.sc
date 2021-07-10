;;; Sierra Script 1.0 - (do not remove this comment)
(script# 5)
(include game.sh)
(use Main)
(use Intrface)
(use GoToSaid)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	beachHuts2 0
)

(local
	[local0 2]
	inLeft = [0 0 0 0 0 142 179 179 163 111 97 99 104 109]
	inUpper = [81 105 117 170 181 81 162 173 173 162 168 147 148 147]
	inRight = [141 141 141 96 178 319 319 319 178 179 110 105 109 141]
	inBottom = [104 116 147 173 189 160 173 189 179 173 173 151 156 160]
	toX = [148 148 148 102 184 153 178 190 175 110 116 148 148 152]
	toY = [104 112 146 172 185 163 166 172 172 167 172 148 155 154]
)
(instance beachHuts2 of Room
	(properties
		picture 5
		horizon 80
		north 7
		east 4
		south 8
		west 8
		vanishingX 0
		vanishingY -50
	)
	
	(method (init)
		(if (== prevRoomNum west)
			((User alterEgo?) edgeHit: 0)
		)
		(super init:)
		(ego init:)
		(self
			setRegions: 301 300
			setFeatures:
				Door__6
				hutWindow
				jungleFeature
				oceanFeature
				beachViewFeature
				horizonFeat
				otherHutDoor
				((Clone hutWindow)
					x: 64
					y: 76
					nsLeft: 47
					nsTop: 67
					nsRight: 81
					nsBottom: 86
					yourself:
				)
				((Clone jungleFeature) x: 175 y: 60)
				((Clone jungleFeature) x: 130 y: 60)
		)
		(switch prevRoomNum
			(east (ego posn: 310 170))
			(6
				(if (> (ego x?) 90)
					(ego posn: 110 167 loop: 0)
				else
					(ego posn: 13 172 loop: 0)
				)
			)
			(else
				(ego posn: 120 82)
			)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((and (> (ego y?) 198) (> (ego x?) 100))
				(self newRoom: 4)
			)
			((== (ego onControl: origin) cMAGENTA)
				(self newRoom: 6)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((and (Said 'look>') (Said '[<around,at][/beach]'))
				(switch (Random 1 5)
					(1
						(Print 5 0)
					)
					(else
						(Print 5 1)
					)
				)
			)
		)
	)
)

(instance Door__6 of RFeature
	(properties
		y 142
		x 93
		heading 909
		nsTop 120
		nsLeft 89
		nsBottom 164
		nsRight 97
		name "Door #6"
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event))
					((Said 'look[<at]')
						(Print 5 2)
					)
					((Said 'open,unlock')
						(if (ego has: iTahitiKey)
							(HandsOff)
							(ego setScript: openDoorScript)
						else
							(Print 5 3)
						)
					)
					((or (Said 'lock') (Said 'lock<to<key<use'))
						(Print 5 4)
					)
					((Said 'knock')
						(Print 5 5)
					)
				)
			)
		)
	)
)

(instance openDoorScript of Script

	(method (changeState newState &tmp i isInRange)
		(switch (= state newState)
			(0
				(= isInRange 0)
				(for ((= i 0)) (< i 14) ((++ i))
					(if
						(ego
							inRect: [inLeft i] [inUpper i] [inRight i] [inBottom i]
						)
						(ego
							setMotion: MoveTo [toX i] [toY i] self
						)
						(= isInRange TRUE)
					)
				)
				(if (not isInRange)
					(HandsOn)
					(curRoom newRoom: 6)
				)
			)
			(1
				(if (and (== (ego x?) 110) (== (ego y?) 167))
					(HandsOn)
					(curRoom newRoom: 6)
				else
					(self init:)
				)
			)
		)
	)
)

(instance otherHutDoor of RFeature
	(properties
		y 87
		x 83
		heading 90
		nsTop 67
		nsLeft 82
		nsBottom 107
		nsRight 85
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event '*/*'))
					((Said 'look[<at]')
						(Print 5 6)
					)
					((Said 'open,unlock')
						(Print 5 7)
					)
					((Said 'knock')
						(Print 5 8)
					)
					((or (Said 'lock') (Said 'lock<to<key<use'))
						(Print 5 4)
					)
				)
			)
		)
	)
)

(instance hutWindow of RFeature
	(properties
		y 131
		x 44
		heading 180
		nsTop 120
		nsBottom 143
		nsRight 88
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((Said 'look[<at]')
						(Print 5 9)
					)
					((Said 'look<in,through')
						(Print 5 10)
					)
				)
			)
			((Said 'look[<in]/building')
				(Print 5 10)
			)
		)
	)
)

(instance jungleFeature of Feature
	(properties
		y 60
		x 300
		heading 235
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/bush]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 5 11)
					)
				)
			)
		)
	)
)

(instance beachViewFeature of Feature
	(properties
		y 160
		x 315
		heading 315
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((and (Said 'look[<at]>') (Said '[/beach]'))
				(Print 5 12)
			)
		)
	)
)

(instance oceanFeature of Feature
	(properties
		y 186
		x 254
		heading 35
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]>')
				(cond 
					((TurnIfSaid self event '[/water,bay]/*'))
					((Said '[/water,bay]')
						(Print 5 13)
					)
				)
			)
		)
	)
)

(instance horizonFeat of Feature
	(properties
		y 55
		x 95
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/horizon]')
				(Print 5 14)
			)
		)
	)
)
