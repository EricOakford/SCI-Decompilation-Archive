;;; Sierra Script 1.0 - (do not remove this comment)
(script# 611)
(include game.sh)
(use Main)
(use ulence)
(use SQRoom)
(use Sq4Feature)
(use Polygon)
(use Motion)
(use System)

(public
	rm611 0
)

(instance rm611 of SQRoom
	(properties
		picture 611
		horizon 90
		north 614
		west 610
	)
	
	(method (init)
		(switch prevRoomNum
			(614 (ego x: 195))
			(610
				(if (< (ego y?) 166) (ego y: 166))
				(globalSound number: 0 vol: 0 stop:)
			)
			(else  (ego x: 24 y: 181))
		)
		(ego init: illegalBits: 0 ignoreActors: TRUE)
		(super init:)
		(self
			setRegions: ULENCE
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 319 189 300 189 263 189 198 189 256 182
						291 163 288 149 291 134 265 86 132 86 149 101 169 107
						179 124 170 140 138 158 0 163
					yourself:
				)
		)
		(building1 init:)
		(building2 init:)
		(graffiti init:)
		(if
		(and (== (ulence status?) 1) (> (Random 0 100) 20))
			(ulence status: 2 bikerComing: TRUE)
			(Load VIEW 633)
			(Load VIEW 635)
			(Load SOUND 50)
		)
		((ScriptID ULENCE 7)
			init:
			nsLeft: 0
			nsTop: 55
			nsBottom: 189
			nsRight: 319
		)
		((ScriptID ULENCE 8)
			init:
			nsLeft: 0
			nsTop: 0
			nsBottom: 55
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
					(ego inRect: 155 172 175 183)
				)
				(HandsOff)
				(ulence status: 3)
				((ScriptID ULENCE 1)
					view: 633
					init:
					hide:
					posn: -30 (- (ego y?) 5)
					setLoop: 0
					setScript: runOver
				)
				(ego setScript: (ScriptID ULENCE 5))
			)
		)
		(super doit: &rest)
	)
)

(instance runOver of Script
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
					setMotion: MoveTo 50 (- (ego y?) 5) self
				)
			)
			(2
				(ulence status: 4)
				(HandsOn)
				((ScriptID ULENCE 1)
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo 171 (- (ego y?) 5) self
				)
			)
			(3
				((ScriptID ULENCE 1) posn: 202 160 setLoop: 6)
				(= cycles 1)
			)
			(4
				((ScriptID ULENCE 1) setMotion: MoveTo 244 141 self)
			)
			(5
				((ScriptID ULENCE 1) posn: 250 131 setLoop: 3)
				(= cycles 1)
			)
			(6
				((ScriptID ULENCE 1) setMotion: MoveTo 250 99 self)
			)
			(7
				((ScriptID ULENCE 1) posn: 225 87 setLoop: 7)
				(= cycles 1)
			)
			(8
				((ScriptID ULENCE 1) setMotion: MoveTo 179 73 self)
			)
			(9
				((ScriptID ULENCE 1)
					setPri: 4
					setLoop: 1
					setMotion: MoveTo 46 73 self
				)
			)
			(10
				((ScriptID ULENCE 6) fade:)
				(music fade:)
				((ScriptID ULENCE 1) hide:)
				(= seconds 3)
			)
			(11
				(if (!= (ulence status?) 6)
					(ulence status: 1 fieldOff: FALSE bikerComing: FALSE egoBusy: FALSE)
					(ego setScript: 0)
					(narrator modNum: 611 say: 1 self)
				)
				(= cycles 2)
			)
			(12 (HandsOn) (self dispose:))
		)
	)
)

(instance graffiti of Sq4Feature
	(properties
		x 30
		y 117
		nsTop 85
		nsBottom 150
		nsRight 60
		sightAngle 45
		lookStr 2
	)
)

(instance building1 of Sq4Feature
	(properties
		x 85
		y 91
		nsTop 32
		nsBottom 151
		nsRight 170
		sightAngle 45
		onMeCheck NEARCHECK
		lookStr 3
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_SMELL (narrator say: 4))
			(V_TASTE (narrator say: 5))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance building2 of Sq4Feature
	(properties
		x 137
		y 46
		nsTop 23
		nsLeft 63
		nsBottom 70
		nsRight 212
		sightAngle 45
		onMeCheck ISNOTHIDDEN
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK (narrator say: 6))
			(V_SMELL (narrator say: 7))
			(V_TASTE (narrator say: 8))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
