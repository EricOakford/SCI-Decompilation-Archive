;;; Sierra Script 1.0 - (do not remove this comment)
(script# 614)
(include game.sh)
(use Main)
(use ulence)
(use SQRoom)
(use Sq4Feature)
(use Polygon)
(use Motion)
(use System)

(public
	rm614 0
)

(local
	local0
)
(instance rm614 of SQRoom
	(properties
		picture 614
		south 611
		west 613
	)
	
	(method (init)
		(switch prevRoomNum
			(611
				(cond 
					((> (ego x?) 245) (HandsOff) (ego posn: 254 241 setMotion: MoveTo 320 174))
					((> (ego x?) 230) (ego x: (Random 202 302)))
					((> (ego x?) 195) (ego x: (Random 85 201)))
					(else (ego x: (Random 20 84)))
				)
			)
			(else  (ego x: 24 y: 181))
		)
		(ego init: ignoreActors: TRUE illegalBits: 0)
		(super init:)
		(self
			setRegions: ULENCE
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 319 189 317 189 317 152 266 161
						206 161 179 153 86 153 83 161 0 161
					yourself:
				)
		)
		(sign1 init:)
		(sign2 init:)
		(gate init:)
		(building init:)
		(if
		(and (> (Random 0 100) 20) (== (ulence status?) 1))
			(ulence status: 2 bikerComing: TRUE)
			(Load VIEW 634)
			(Load VIEW 635)
			(Load SOUND 50)
		else
			(ulence bikerComing: FALSE)
		)
		((ScriptID ULENCE 7)
			init:
			nsLeft: 0
			nsTop: 86
			nsBottom: 189
			nsRight: 319
		)
		((ScriptID ULENCE 8)
			init:
			nsLeft: 0
			nsTop: 0
			nsBottom: 86
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
			(ulence deathLoop: 0)
			(self setScript: (ScriptID ULENCE 3))
		)
		(cond 
			(script 0)
			(
				(and
					(== (ulence status?) 2)
					(ego inRect: 20 171 46 182)
					local0
				)
				(HandsOff)
				(ulence status: 3)
				((ScriptID ULENCE 1)
					init:
					view: 634
					hide:
					posn: 308 217
					setScript: runOverScript
				)
				(= local0 0)
				(ego setScript: (ScriptID ULENCE 4))
			)
		)
		(super doit: &rest)
		(if (> (ego x?) 60) (= local0 1))
	)
)

(instance runOverScript of Script
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
				(= seconds 3)
			)
			(1
				((ScriptID ULENCE 6) init: fade:)
				((ScriptID ULENCE 1)
					show:
					setLoop: 7
					setMotion: MoveTo 246 (- (ego y?) 5) self
				)
			)
			(2
				(ulence status: 4)
				(HandsOn)
				((ScriptID ULENCE 1)
					setLoop: 1
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo -51 (- (ego y?) 5) self
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

(instance building of Sq4Feature
	(properties
		x 155
		y 84
		nsTop 7
		nsBottom 161
		nsRight 311
		sightAngle 45
		onMeCheck NEARCHECK
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK (narrator say: 2))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 2))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gate of Sq4Feature
	(properties
		x 125
		y 115
		nsTop 76
		nsLeft 60
		nsBottom 154
		nsRight 191
		sightAngle 45
		onMeCheck ISNOTHIDDEN
		lookStr 3
	)
)

(instance sign1 of Sq4Feature
	(properties
		x 125
		y 59
		nsTop 45
		nsLeft 37
		nsBottom 73
		nsRight 214
		sightAngle 45
		onMeCheck (| ISNOTHIDDEN NEARCHECK)
		lookStr 4
	)
)

(instance sign2 of Sq4Feature
	(properties
		x 126
		y 130
		nsTop 122
		nsLeft 108
		nsBottom 138
		nsRight 145
		sightAngle 45
		onMeCheck FARCHECK
		lookStr 5
	)
)
