;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include game.sh)
(use Main)
(use SQRoom)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use System)

(public
	rm21 0
)

(instance rm21 of SQRoom
	(properties
		lookStr {This is the northeast corner of the boneyard. Bones and sand dominate the scenery. At the east end of the vertebral path are two extremely pointed spurs of broken rib.}
		picture 21
		horizon 20
		north 38
		east 138
		south 24
		west 20
		walkOffTop 1
	)
	
	(method (init)
		(if (== currentFloor 2)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 119 122 119 207 136 266 148 268 163 250 167 212 168
							183 168 152 172 152 174 143 177 139 185 126 186 112 185
							91 188 84 185 68 182 70 178 47 171 10 173 0 176
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 106 38 98 122 83 149 86 132 76 109 77 97 76 91 80
							71 84 58 80 41 83 34 82 0 91 0 0 319 0 319 189 0 189
						yourself:
					)
			)
			(LoadMany VIEW 45)
		)
		(switch prevRoomNum
			(west (= style SCROLLLEFT) (HandsOn))
			(south (= style SCROLLUP) (HandsOn))
			(else  (= style FADEOUT))
		)
		(ego init:)
		(self setRegions: KERONA)
		(thingy init:)
		(super init:)
	)
	
	(method (doit)
		(cond 
			(script 0)
			(
			(and (== currentFloor 1) (ego inRect: 89 75 116 89)) (SolvePuzzle 2 fEnterCaves) (self setScript: enterCaves))
		)
		(super doit:)
	)
)

(instance enterCaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 103 83 self)
			)
			(1
				(theMusic number: 414 loop: 1 play:)
				(ego
					view: 45
					loop: 0
					cycleSpeed: 3
					moveSpeed: 3
					setCycle: EndLoop self
				)
			)
			(2 (curRoom newRoom: 29))
		)
	)
)

(instance thingy of Feature
	(properties
		x 159
		y 64
		description {shiny thingy}
		onMeCheck $0400
		approachX 142
		approachY 84
		lookStr {It looks interesting. Although hard to tell from where you are standing, it may be a sign. If you got closer, maybe you could read it.}
	)
	
	(method (init)
		(if (== currentFloor 1)
			(self approachVerbs: verbDo verbTalk verbSmell verbTaste)
		else
			(= y 150)
		)
		(super init: &rest)
	)
)
