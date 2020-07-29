;;; Sierra Script 1.0 - (do not remove this comment)
(script# 800)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Intrface)
(use RandCyc)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm800 0
)

(local
	local0
)
(instance rm800 of LLRoom
	(properties
		lookStr {You are outside the studios of `K-Rap Radio.' It appears to be an amazingly small building for a station of such importance.}
		picture 800
		north 820
	)
	
	(method (init)
		(LoadMany VIEW 800 810 571)
		(LoadMany SOUND 800 801 802 191 192)
		(super init:)
		(switch prevRoomNum
			(820
				(self style: PIXELDISSOLVE)
				(ego
					init:
					normalize: 571
					posn: 153 143
					setHeading: 180
					edgeHit: 0
				)
				(theMusic setVol: lastVolume)
				(HandsOn)
			)
			(200
				(theMusic stop:)
				(WrapMusic firstSound: 800 lastSound: 802 init:)
				(self style: 7)
				(self setScript: sExitLimo)
			)
			(else 
				(theMusic stop:)
				(WrapMusic firstSound: 800 lastSound: 802 init:)
				(= lastVolume (theMusic vol?))
				(if (== numColors 256)
					(self style: PIXELDISSOLVE)
				else
					(self style: DISSOLVE)
				)
				(ego
					init:
					normalize: 571
					posn: 155 153
					setHeading: 180
					edgeHit: 0
				)
				(HandsOn)
				(self setScript: exitDreamScript)
			)
		)
		(bush1 init:)
		(bush2 init:)
		(bush3 init:)
		(bush4 init:)
		(drumpicture init:)
		(tower init:)
		(tree1 init:)
		(limo init: approachVerbs: verbDo verbUse)
		(door init: openScript: sIntoBuilding)
		(neonSign init:)
		(cone1 init:)
		(cone2 init:)
		(cast eachElementDo: #checkDetail)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						139 0
						139 144
						5 147
						5 153
						10 156
						78 156
						103 153
						129 152
						143 162
						184 162
						184 154
						200 151
						282 149
						294 148
						314 144
						253 143
						167 143
						167 0
						319 0
						319 189
						0 189
					yourself:
				)
		)
	)
	
	(method (newRoom n)
		(if (== n 820)
			(ego edgeHit: 1)
		)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(super newRoom: n)
	)
)

(instance limo of Feature
	(properties
		x 62
		y 161
		nsTop 148
		nsBottom 174
		nsRight 125
		description {your limousine}
		approachX 26
		approachY 152
		lookStr {Your limousine awaits your return.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(rm800 setScript: sEnterLimo)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance neonSign of Prop
	(properties
		x 156
		y 27
		description {the K-RAP sign}
		lookStr {High above your head, K-RAP's neon sign illuminates the Baltimore night.}
		view 800
		cel 8
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward checkDetail:)
	)
)

(instance door of Door
	(properties
		x 153
		y 138
		description {the door}
		approachX 153
		approachY 144
		lookStr {K-RAP's doors are always open.}
		view 800
		loop 1
		priority 9
		signal fixPriOn
		entranceTo 820
		moveToX 153
		moveToY 140
		enterType 0
		exitType 0
	)
)

(instance cone1 of Prop
	(properties
		x 71
		y 118
		description {the left speaker}
		lookStr {K-RAP's loudspeakers broadcast the station's current programming.}
		view 800
		loop 2
		detailLevel 5
	)
	
	(method (init)
		(super init:)
		(self setCycle: RandCycle checkDetail:)
	)
)

(instance cone2 of Prop
	(properties
		x 235
		y 114
		description {the right speaker}
		lookStr {K-RAP's loudspeakers broadcast the station's current programming.}
		view 800
		loop 3
		detailLevel 5
	)
	
	(method (init)
		(super init:)
		(self setCycle: RandCycle checkDetail:)
	)
)

(instance bush1 of Feature
	(properties
		x 50
		y 136
		nsTop 125
		nsLeft 36
		nsBottom 147
		nsRight 64
		description {the bush}
		sightAngle 40
		lookStr {Someone planted these bushes right in the middle of this busy sidewalk!}
	)
)

(instance bush2 of Feature
	(properties
		x 107
		y 135
		nsTop 123
		nsLeft 89
		nsBottom 148
		nsRight 125
		description {the bush}
		sightAngle 40
		lookStr {Someone planted these bushes right in the middle of this busy sidewalk!}
	)
)

(instance bush3 of Feature
	(properties
		x 210
		y 134
		nsTop 117
		nsLeft 196
		nsBottom 151
		nsRight 224
		description {the bush}
		sightAngle 40
		lookStr {Someone planted these bushes right in the middle of this busy sidewalk!}
	)
)

(instance bush4 of Feature
	(properties
		x 283
		y 123
		nsTop 95
		nsLeft 268
		nsBottom 151
		nsRight 299
		description {the bush}
		sightAngle 40
		lookStr {Someone planted these bushes right in the middle of this busy sidewalk!}
	)
)

(instance drumpicture of Feature
	(properties
		x 161
		y 81
		nsTop 65
		nsLeft 112
		nsBottom 98
		nsRight 210
		description {the mural}
		sightAngle 40
		lookStr {K-RAP radio has a mural of musicians on their wall.}
	)
)

(instance tower of Feature
	(properties
		x 262
		y 36
		nsLeft 225
		nsBottom 90
		nsRight 303
		description {the tower}
		sightAngle 40
		lookStr {The tower holds the antennas that broadcast K-RAP's stereo multiplexed signal to millions of people (some of whom listen!).}
	)
)

(instance tree1 of Feature
	(properties
		x 24
		y 92
		nsTop 44
		nsBottom 141
		nsRight 49
		description {the trees}
		sightAngle 40
		lookStr {A clump of white birch trees grows just outside K-RAP.}
	)
)

(instance logo of Prop
	(properties
		x 164
		y 37
		view 810
		cel 12
		cycleSpeed 12
		detailLevel 3
	)
)

(instance city1 of Prop
	(properties
		x 38
		y 59
		view 810
		loop 2
		cycleSpeed 24
		detailLevel 5
	)
)

(instance city2 of Prop
	(properties
		x 193
		y 53
		view 810
		loop 3
		cycleSpeed 24
		detailLevel 5
	)
)

(instance plane of Actor
	(properties
		x 96
		y 13
		view 810
		loop 1
		moveSpeed 22
	)
)

(instance sIntoBuilding of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego hide:)
				(HandsOff)
				(cast eachElementDo: #hide)
				(SetFFRoom 820)
				(if (not (Btst fEnteredKRAP))
					(rm800 style: IRISOUT)
					(curRoom drawPic: 810 picture: 810 curPic: 810)
					(logo init: setCycle: Forward)
					(city1 init: setCycle: Forward)
					(city2 init: setCycle: Forward)
					(plane
						init:
						setCycle: Forward
						setMotion: MoveTo -20 5
						setStep: 1 1
					)
					(cast eachElementDo: #checkDetail)
					(Bset fEnteredKRAP)
					(= seconds 5)
				else
					(curRoom newRoom: 820)
				)
			)
			(1
				(TimePrint 800 0 #at -1 185 #width 280 #dispose self)
			)
			(2 (= ticks 180))
			(3
				(TimePrint 800 1 #at -1 185 #width 280 #dispose self)
			)
			(4 (= ticks 180))
			(5
				(TimePrint 800 2 #at -1 185 #width 280 #dispose self)
			)
			(6 (= ticks 180))
			(7 (curRoom newRoom: 820))
		)
	)
)

(instance exitDreamScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 6))
			(1
				(self dispose:)
				(if
					(Print 800 3
						#button {Save} 1
						#button {Nah, Why Bother?} 0
						#title {AL says}
						#at -1 20
					)
					(theGame save:)
				)
			)
		)
	)
)

(instance sExitLimo of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(HandsOff)
				(theMusic2 number: 191 setLoop: 1 play:)
				(= ticks 50)
			)
			(2
				(ego
					init:
					normalize: 571
					posn: 26 174
					setHeading: 0
					edgeHit: 0
				)
				(= ticks 123)
			)
			(3
				(theMusic2 number: 192 setLoop: 1 play:)
				(ego setMotion: MoveTo 26 152 self)
			)
			(4
				(HandsOn)
				(ego normalize: 571 setLoop: -1 setHeading: 0)
				(self dispose:)
			)
		)
	)
)

(instance sEnterLimo of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setLoop: -1 setHeading: 180 self)
			)
			(1
				(ego setMotion: MoveTo 26 174 self)
			)
			(2
				(theMusic2 number: 191 setLoop: 1 play:)
				(= ticks 50)
			)
			(3
				(ego hide:)
				(theMusic2 number: 192 setLoop: 1 play:)
				(= ticks 100)
			)
			(4
				(theMusic fade: 0 15 12 1)
				(curRoom newRoom: 200)
			)
		)
	)
)
