;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmDarkAlley) ;170
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use Polygon)
(use Block)
(use Feature)
(use LoadMany)
(use Wander)
(use Chase)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm170 0
)

(instance rm170 of LLRoom
	(properties
		picture rmDarkAlley
	)
	
	(method (init &tmp temp0)
		(Load SCRIPT CHASE)
		(LoadMany VIEW 172 171 812)
		(LoadMany SOUND 170 192 171 172 173)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 174 0 0 319 0 319 171 253 138 221 146 188 133 67 134 0 175
					yourself:
				)
		)
		(theMusic number: 170 vol: 127 loop: -1 flags: 1)
		(switch prevRoomNum
			(rmOutsideBar
				(if (> (ego x?) 160)
					(self west: prevRoomNum)
				else
					(self east: prevRoomNum)
				)
			)
			(rmOutside7_11 (self east: prevRoomNum))
			(rmOutsideDisco (self west: prevRoomNum))
			(rmOutsideCasino (self east: prevRoomNum))
			(rmOutsideChapel (self west: prevRoomNum))
			(else  (self east: rmOutsideBar))
		)
		(sfxFight init:)
		(sfxDizzy init:)
		(sfxBounce init:)
		(aThug
			init:
			cycleSpeed: (theGame egoMoveSpeed?)
			moveSpeed: (theGame egoMoveSpeed?)
			setScript: sChased
		)
		(ego init: y: 180)
		(super init:)
		(dumpster init:)
		(fence init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 170 0)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance sChased of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(!= (aThug moveSpeed?) (theGame egoMoveSpeed?))
			(aThug
				cycleSpeed: (theGame egoMoveSpeed?)
				moveSpeed: (theGame egoMoveSpeed?)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic play:)
				(aThug setPri: 1 loop: 1 setMotion: MoveTo 223 131 self)
			)
			(1
				(aThug
					setPri: 3
					setCycle: Walk
					setMotion: MoveTo 167 131 self
				)
			)
			(2
				(aThug
					setCycle: Walk
					setPri: -1
					setMotion: Chase ego 20 self
				)
			)
			(3
				(curRoom setScript: sMugged)
				(self dispose:)
			)
		)
	)
)

(instance sMugged of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego hide:)
				(= speed (+ 4 howFast))
				(sfxFight play:)
				(aThug
					view: 171
					setLoop: 0
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo 175 160 self
				)
			)
			(1
				(aThug setLoop: 1 setMotion: 0)
				(= cycles 3)
			)
			(2
				(aThug
					observeBlocks: punkCircle
					setLoop: 0
					setCycle: Forward
					setMotion: Wander
				)
				(= seconds 5)
			)
			(3
				(aThug setLoop: 1 setMotion: 0)
				(= cycles 3)
			)
			(4
				(aThug
					observeBlocks: punkCircle
					setLoop: 0
					setMotion: Wander
				)
				(= seconds 5)
			)
			(5
				(aThug setLoop: 1 setMotion: 0)
				(= cycles 3)
			)
			(6
				(aThug
					observeBlocks: punkCircle
					setLoop: 0
					setMotion: MoveTo 116 165 self
				)
			)
			(7
				(aThug2
					init:
					setCycle: Walk
					setMotion: MoveTo 184 249 self
				)
			)
			(8
				(theMusic stop:)
				(sfxFight stop:)
				(aThug hide: z: 1000)
				(sfxDizzy play:)
				(ego
					show:
					view: 171
					setLoop: 2
					x: 104
					y: 163
					cycleSpeed: 1
					setCycle: CycleTo 9 1 self
				)
			)
			(9
				(sfxDizzy stop:)
				(sfxBounce play:)
				(ego setCycle: EndLoop self)
			)
			(10
				(sfxBounce stop:)
				(= seconds 4)
			)
			(11
				(Print 170 1)
				(= seconds 4)
			)
			(12
				(Print 170 2)
				(= seconds 2)
			)
			(13
				(globalSound number: 192 setLoop: -1 flags: 1 play:)
				(sfxGroundOpens play:)
				(ego view: 812 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(14
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(curRoom newRoom: rmWorkshop)
			)
		)
	)
)

(instance sfxGroundOpens of Sound
	(properties
		flags mNOPAUSE
		number 191
	)
)

(instance sfxFight of Sound
	(properties
		flags mNOPAUSE
		number 171
		loop -1
	)
)

(instance sfxDizzy of Sound
	(properties
		flags mNOPAUSE
		number 172
	)
)

(instance sfxBounce of Sound
	(properties
		flags mNOPAUSE
		number 173
	)
)

(instance punkCircle of Cage
	(properties
		top 137
		left 65
		bottom 189
		right 265
	)
)

(instance aThug2 of Actor
	(properties
		x 124
		y 174
		view 172
	)
)

(instance aThug of Actor
	(properties
		x 223
		y 150
		description {the mugger}
		sightAngle 40
		lookStr {It appears he wants something -- YOU! Quick! Get out of here, Larry!}
		yStep 3
		view 172
		loop 1
		priority 2
		xStep 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(Print 170 3)
				(Print 170 4 #at -1 140)
			)
			(verbDo
				(Print 170 5)
				(Print 170 6 #at -1 140)
			)
			(verbZipper (Print 170 7))
			(verbTaste (Print 170 8))
			(verbUse (Print 170 9))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance dumpster of Feature
	(properties
		x 222
		y 107
		nsTop 84
		nsLeft 191
		nsBottom 131
		nsRight 254
		description {the dumpster}
		sightAngle 40
		lookStr {You think you've seen this dumpster before!}
	)
)

(instance fence of Feature
	(properties
		x 144
		y 71
		nsTop 41
		nsLeft 84
		nsBottom 102
		nsRight 205
		description {the fence}
		sightAngle 40
		lookStr {You think you've seen this fence before!}
	)
)
