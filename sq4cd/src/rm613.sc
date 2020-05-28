;;; Sierra Script 1.0 - (do not remove this comment)
(script# 613)
(include game.sh)
(use Main)
(use ulence)
(use SQRoom)
(use Sq4Dialog)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use Motion)
(use System)

(public
	rm613 0
)

(instance rm613 of SQRoom
	(properties
		picture 613
		east 614
		south 610
		west 612
	)
	
	(method (init)
		(Load VIEW 305)
		(Load VIEW 378)
		(Load VIEW 617)
		(Load SOUND 124)
		(Load SOUND 125)
		(Load SOUND 142)
		(switch prevRoomNum
			(609 (ego x: 14 y: 185))
			(610
				(if (Btst fKickBikes)
					(ulence status: 2 bikerComing: TRUE)
					(Load VIEW 633)
					(Load VIEW 635)
					(Load SOUND 50)
					(self setScript: bringInBike)
				)
				(globalSound number: 0 vol: 0 stop:)
			)
			(612
				(if (< (ego y?) 146) (ego y: 134))
			)
			(614
				(if (> (ego y?) 171) (ego y: 171))
			)
			(else 
				(ego setLoop: 0 setCel: 0 posn: 1000 1000)
				(if (Btst fKickBikes) (ulence status: 1))
				(music flags: mNOPAUSE)
				(globalSound flags: mNOPAUSE)
				(self setScript: landShip)
			)
		)
		(door init: setCel: 0)
		(ship init:)
		(ego init: illegalBits: 0 ignoreActors: TRUE)
		(super init:)
		(self
			setRegions: ULENCE
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 319 162 261 160 225 152 191 136 197 133
						205 127 190 122 149 121 144 140 104 140 59 154 38 146
						40 124 0 124
					yourself:
				)
		)
		(building init:)
		(if
		(and (> (Random 0 100) 20) (== (ulence status?) 1))
			(ulence status: 2 bikerComing: TRUE)
			(Load VIEW 633)
			(Load VIEW 635)
			(Load SOUND 50)
		else
			(ulence bikerComing: 0)
		)
		((ScriptID ULENCE 7)
			init:
			nsLeft: 0
			nsTop: 91
			nsBottom: 189
			nsRight: 319
		)
		((ScriptID ULENCE 8)
			init:
			nsLeft: 0
			nsTop: 0
			nsBottom: 90
			nsRight: 319
		)
	)
	
	(method (doit)
		(if
			(and
				(== (ulence status?) 4)
				(< ((ScriptID ULENCE 1) distanceTo: ego) 15)
			)
			(HandsOff)
			(ulence status: 6)
			(ego setScript: 0)
			(ulence deathLoop: 1)
			(self setScript: (ScriptID ULENCE 3))
		)
		(cond 
			(script 0)
			(
				(and
					(== (ulence status?) 2)
					(ego inRect: 220 170 259 182)
				)
				(HandsOff)
				(ulence status: 3)
				((ScriptID ULENCE 1)
					view: 633
					init:
					hide:
					posn: 0 (- (ego y?) 5)
					setLoop: 0
					setScript: runOver1
				)
				(ego setScript: (ScriptID ULENCE 5))
			)
		)
		(super doit: &rest)
	)
)

(instance landShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door setPri: 14)
				(ego
					view: 378
					posn: 106 143
					illegalBits: 0
					ignoreActors: TRUE
					normal: 0
				)
				(= cycles 1)
			)
			(1
				(globalSound number: 838 loop: 1 vol: 127 play:)
				(ego show:)
				(= seconds 2)
			)
			(2
				(globalSound number: 124 loop: 1 play: self)
			)
			(3
				(globalSound number: 142 loop: 1 play:)
				(door setCycle: EndLoop self)
			)
			(4
				(globalSound stop:)
				(= seconds (Random 2 4))
			)
			(5 (ego setCycle: EndLoop self))
			(6
				(NormalEgo (ego loop?) 0)
				(ship priority: -1 stopUpd:)
				(= cycles 1)
			)
			(7 (ego setHeading: 135 self))
			(8
				(ego
					setLoop: 4
					heading: 135
					setMotion: MoveTo 110 142 self
				)
			)
			(9
				(globalSound play:)
				(door setPri: 5 setCycle: BegLoop self)
			)
			(10
				(globalSound number: 125 play:)
				(NormalEgo (ego loop?) 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: PolyPath 111 147 self
				)
			)
			(1
				(globalSound vol: 127 number: 142 loop: 1 play:)
				(door setCycle: EndLoop self)
			)
			(2
				(globalSound stop:)
				(door setPri: 14)
				(ego
					setPri: 5
					posn: 110 121
					setCel: 0
					view: 378
					setLoop: 1
					cycleSpeed: 12
				)
				(= cycles 1)
			)
			(3 (ego setCycle: EndLoop self))
			(4
				(globalSound vol: 127 play:)
				(door setCycle: BegLoop self)
			)
			(5
				(globalSound number: 125 vol: 127 play:)
				(music fade:)
				(DrawStatus {_} myTextColor16 0)
				(curRoom newRoom: 531)
			)
		)
	)
)

(instance runOver1 of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ulence egoBusy: 1)
				(music
					vol: 25
					number: 50
					loop: -1
					playBed:
					fade: 127 10 10 0
				)
				(= seconds 3)
			)
			(1
				((ScriptID ULENCE 6) init: play:)
				((ScriptID ULENCE 1)
					show:
					setMotion: MoveTo 65 (- (ego y?) 5) self
				)
			)
			(2
				(ulence status: 4)
				(HandsOn)
				((ScriptID ULENCE 1)
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo 338 (- (ego y?) 5) self
				)
			)
			(3
				(music fade:)
				((ScriptID ULENCE 6) fade:)
				((ScriptID ULENCE 1) hide:)
				(= seconds 3)
			)
			(4
				(if (!= (ulence status?) 6)
					(ulence status: 1 fieldOff: FALSE bikerComing: FALSE egoBusy: FALSE)
					(ego setScript: 0)
					(narrator modNum: 611 say: 1 self)
				)
				(= cycles 2)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance bringInBike of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego
					posn: (ego x?) 238
					setMotion: MoveTo (ego x?) 185 self
				)
			)
			(1
				(HandsOff)
				(ulence status: 3)
				((ScriptID ULENCE 1)
					view: 632
					init:
					hide:
					posn: 364 179
					setLoop: 1
					setScript: runOver2
				)
				(ego setScript: (ScriptID ULENCE 4))
				(self dispose:)
			)
		)
	)
)

(instance runOver2 of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ulence egoBusy: TRUE)
				(music
					vol: 25
					number: 50
					loop: -1
					playBed:
					fade: 127 10 10 0
				)
				(= seconds 1)
			)
			(1
				((ScriptID ULENCE 6) init: play:)
				((ScriptID ULENCE 1) show: setMotion: MoveTo 164 179 self)
			)
			(2
				(ulence status: 4)
				(HandsOn)
				((ScriptID ULENCE 1)
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo -56 (- (ego y?) 5) self
				)
			)
			(3
				(music fade:)
				((ScriptID ULENCE 6) fade:)
				((ScriptID ULENCE 1) hide:)
				(= seconds 3)
			)
			(4
				(if (!= (ulence status?) 6)
					(ulence status: 1 fieldOff: FALSE bikerComing: FALSE egoBusy: FALSE)
					(ego setScript: 0)
					(narrator modNum: 611 say: 1 self)
				)
				(= cycles 2)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance ship of Sq4Prop
	(properties
		x 93
		y 146
		view 617
		signal (| ignrAct ignrHrz)
	)
	
	(method (init)
		(super init:)
		(self setPri: 4 addToPic:)
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_TASTE (narrator say: 1))
			(V_LOOK (narrator say: 2))
			(V_DO
				(if (== (ulence egoBusy?) 1)
					(SQ4Print 613 0)
				else
					(HandsOff)
					(ulence fieldOff: TRUE)
					(curRoom setScript: enterPod)
				)
			)
			(V_SMELL (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Sq4Prop
	(properties
		x 80
		y 107
		view 617
		loop 1
		priority 5
		signal (| ignrAct ignrHrz fixPriOn)
	)
)

(instance building of Sq4Feature
	(properties
		y 155
		nsTop 29
		nsLeft 194
		nsBottom 155
		nsRight 319
		sightAngle 180
		onMeCheck NEARCHECK
		lookStr 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_SMELL (narrator say: 5))
			(V_TASTE (narrator say: 6))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
