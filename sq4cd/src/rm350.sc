;;; Sierra Script 1.0 - (do not remove this comment)
(script# 350)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use MoveCyc)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	rm350 0
)

(local
	[spitCycle 41] = [3 3 177 138 3 0 177 138 3 1 177 138 3 1 177 144 3 1 177 148 3 1 177 158 3 1 177 168 3 1 177 178 3 1 177 188 0 0 -100 -100 -32768]
	[dieCycle 41] = [0 0 135 110 0 1 135 110 0 2 135 110 0 3 135 110 0 4 135 110 3 0 162 104 3 1 160 104 3 2 158 104 3 3 156 104 3 4 154 104 -32768]
	[tongueCycle 41] = [1 0 135 110 1 1 135 110 1 2 135 110 1 3 135 110 1 4 135 110 2 0 162 104 2 1 160 104 2 2 158 104 2 3 156 104 2 4 154 104 -32768]
)
(instance rm350 of SQRoom
	(properties
		picture 350
	)
	
	(method (init)
		(ShowStatus 10)
		(LoadMany VIEW 350 351 338)
		(LoadMany SOUND 140 103 840)
		(bSound init:)
		(theMaw init:)
		(switch prevRoomNum
			(341
				(rogerTongue init:)
				(self setScript: dieScript)
				(theMouth init:)
				(finDrip init: setScript: finDripScript)
			)
			(335
				(if (Btst fSlugBait)
					(rogerTongue init:)
					(self setScript: dieScript)
					(theMouth init:)
					(finDrip init: setScript: finDripScript)
					(headSwell init:)
					(steam init:)
				else
					(theMouth init:)
					(egoStruggle init: setCycle: Forward)
					(finDrip init: setScript: finDripScript)
					(eye init: setPri: 13 setScript: grabEgoScript)
					(headSwell init:)
					(steam init:)
				)
			)
			(else 
				(theMouth init:)
				(super init:)
				(egoStruggle init: setCycle: Forward)
				(finDrip init: setScript: finDripScript)
				(eye init: setPri: 13 setScript: grabEgoScript)
			)
		)
		(super init:)
	)
)

(instance grabEgoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMouth setCycle: EndLoop self)
			)
			(1
				(spit init: setPri: 12 setCycle: MoveCycle @spitCycle)
				(eye cel: 1)
				(tentacles init: setCycle: Forward self)
				(= seconds 5)
			)
			(2
				(tentacles dispose:)
				(theMouth setCycle: EndLoop self)
			)
			(3
				(spit setCycle: MoveCycle @spitCycle)
				(eye cel: 0)
				(tongue init: setCycle: EndLoop self)
			)
			(4 (curRoom newRoom: 341))
		)
	)
)

(instance dieScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== (self state?) 2) (== (theMouth cel?) 0))
			(aSound playBed:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theMouth setCel: 3)
				(if (== ((inventory at: 7) owner?) ego)
					(self setScript: feedSlugScript)
				else
					(HandsOff)
					(rogerTongue setCycle: MoveCycle @dieCycle self)
				)
			)
			(1
				(HandsOn)
				(rogerTongue dispose:)
				(= cycles 1)
			)
			(2
				(music stop:)
				(aSound init:)
				(theMouth setCycle: ForwardCounter 8 self)
			)
			(3
				(theMouth setCycle: BegLoop self)
			)
			(4
				(aSound dispose:)
				(= cycles 5)
			)
			(5
				(cSound init:)
				(theMouth setCycle: BegLoop)
				(cSound play:)
				(= seconds 3)
			)
			(6 (EgoDead iconSLUG deathGENERIC))
		)
	)
)

(instance feedSlugScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== (self state?) 3) (== (theMouth cel?) 0))
			(aSound playBed:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rogerTongue setCycle: MoveCycle @tongueCycle self)
			)
			(1
				(HandsOn)
				(theIconBar curIcon: (theIconBar at: ICON_ITEM))
				(theGame setCursor: 964 TRUE)
				(rogerTongue setLoop: 2 cel: 0 setCycle: Forward)
				(= seconds 7)
			)
			(2
				(HandsOff)
				(tongue dispose:)
				(rogerTongue dispose:)
				(= cycles 1)
				(aSound init:)
			)
			(3
				(music stop:)
				(theMouth setCycle: ForwardCounter 4 self)
			)
			(4
				(theMouth setCycle: BegLoop self)
			)
			(5
				(aSound dispose:)
				(headSwell z: 0)
				(theMouth dispose:)
				(steam z: 0)
				(= cycles 5)
			)
			(6
				(steam setCycle: ForwardCounter 3 self)
				(bSound play:)
			)
			(7 (EgoDead iconSLUG deathSLUG))
		)
	)
)

(instance throwTankScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== (self state?) 2) (== (theMouth cel?) 0))
			(aSound playBed:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(rogerTongue
					setCycle: 0
					setLoop: 4
					x: 167
					y: 132
					setCycle: EndLoop self
				)
				(ego put: iOxygenTank)
			)
			(1
				(SolvePuzzle fKillSlug 5)
				(tongue dispose:)
				(theMouth setCycle: BegLoop)
				(rogerTongue x: 225 y: 136 setLoop: 5 setCycle: EndLoop self)
				(aSound init:)
			)
			(2
				(music stop:)
				(theMouth setCycle: ForwardCounter 2 self)
			)
			(3
				(aSound dispose:)
				(headSwell z: 0)
				(theMouth dispose:)
				(steam z: 0)
				(= cycles 5)
			)
			(4
				(steam setCycle: ForwardCounter 3 self)
				(bSound play:)
			)
			(5 (curRoom newRoom: 335))
		)
	)
)

(instance finDripScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(finDrip setCycle: Forward)
				(= cycles 5)
			)
		)
	)
)

(instance theMaw of Sq4Feature
	(properties
		x 151
		y 97
		nsTop 63
		nsLeft 121
		nsBottom 131
		nsRight 182
		sightAngle 90
		lookStr 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TANK
				(curRoom setScript: throwTankScript)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance aSound of Sound
	(properties
		number 140
	)
)

(instance bSound of Sound
	(properties
		number 103
		vol 100
	)
)

(instance cSound of Sound
	(properties
		number 840
	)
)

(instance egoStruggle of Sq4Prop
	(properties
		x 292
		y 97
		view 350
	)
)

(instance theMouth of Sq4Prop
	(properties
		x 148
		y 101
		sightAngle 180
		view 350
		loop 1
		lookStr 2
	)
	
	(method (doVerb theVerb)
		(theMaw doVerb: theVerb &rest)
	)
)

(instance finDrip of Sq4Prop
	(properties
		x 13
		y 187
		view 350
		loop 2
		cycleSpeed 18
		lookStr 3
	)
)

(instance spit of Sq4Prop
	(properties
		x 177
		y 138
		view 350
		loop 3
		cycleSpeed 12
		lookStr 4
	)
)

(instance eye of Sq4Prop
	(properties
		x 144
		y 82
		view 350
		loop 4
		priority 13
		lookStr 5
	)
)

(instance tongue of Sq4Prop
	(properties
		x 157
		y 99
		view 350
		loop 6
		lookStr 6
	)
	
	(method (doVerb theVerb)
		(theMaw doVerb: theVerb &rest)
	)
)

(instance tentacles of Sq4Prop
	(properties
		x 157
		y 96
		view 350
		loop 7
		lookStr 6
	)
	
	(method (doVerb theVerb)
		(theMaw doVerb: theVerb &rest)
	)
)

(instance rogerTongue of Sq4Actor
	(properties
		x 135
		y 110
		view 351
		priority 4
		signal fixPriOn
		lookStr 7
	)
	
	(method (doVerb theVerb)
		(theMaw doVerb: theVerb &rest)
	)
)

(instance headSwell of Sq4Prop
	(properties
		x 150
		y 142
		z 1000
		sightAngle 180
		view 338
		priority 13
		signal (| ignrAct fixPriOn)
		lookStr 8
	)
)

(instance steam of Sq4Prop
	(properties
		x 186
		y 92
		z 1000
		sightAngle 180
		view 338
		loop 1
		priority 13
		signal (| ignrAct fixPriOn)
		lookStr 9
	)
)
