;;; Sierra Script 1.0 - (do not remove this comment)
(script# 612)
(include game.sh)
(use Main)
(use ulence)
(use SQRoom)
(use Polygon)
(use Motion)
(use System)

(public
	rm612 0
)

(instance rm612 of SQRoom
	(properties
		picture 612
		east 613
		south 609
	)
	
	(method (init)
		(switch prevRoomNum
			(609
				(ego x: (- (ego x?) 30))
				(if (< (ego x?) 95) (ego x: 95))
			)
			(613
				(if (< (ego y?) 144) (ego y: 132))
			)
			(else  (ego x: 280 y: 164))
		)
		(ego init: illegalBits: 0 ignoreActors: TRUE)
		(super init:)
		(Load VIEW 636)
		(self
			setRegions: ULENCE
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 189 59 189 59 182 186 120 194 118 319 118 319 0 0 0
					yourself:
				)
		)
		(if (== (ulence status?) 1)
			(ulence status: 2 bikerComing: TRUE)
			(Load VIEW 632)
			(Load VIEW 635)
			(Load VIEW 634)
			(Load SOUND 50)
		else
			(ulence bikerComing: FALSE)
		)
		((ScriptID ULENCE 7)
			init:
			nsLeft: 0
			nsTop: 87
			nsBottom: 189
			nsRight: 319
		)
		((ScriptID ULENCE 8)
			init:
			nsLeft: 0
			nsTop: 0
			nsBottom: 87
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
			(ulence deathLoop: 0)
			(ego setScript: 0)
			(curRoom setScript: (ScriptID ULENCE 3))
		)
		(cond 
			(script 0)
			(
				(and
					(> (Random 0 100) 95)
					(== (ulence status?) 2)
					(ego inRect: 90 160 152 182)
				)
				(HandsOff)
				(ulence status: 3 fieldOff: TRUE)
				((ScriptID ULENCE 1)
					init:
					hide:
					posn: 320 116
					setLoop: 5
					setScript: runOver
				)
				(ego setScript: (ScriptID ULENCE 4))
			)
			(
				(and
					(> (Random 0 100) 95)
					(== (ulence status?) 2)
					(ego inRect: 261 172 284 182)
				)
				(HandsOff)
				(ulence status: 3 fieldOff: TRUE)
				((ScriptID ULENCE 1)
					view: 634
					init:
					hide:
					setLoop: 6
					posn: 39 226
					setScript: runOver2
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
					setMotion: MoveTo 218 (- (ego y?) 5) self
				)
			)
			(2
				(ulence status: 4)
				(HandsOn)
				((ScriptID ULENCE 1)
					setLoop: 1
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo 104 (- (ego y?) 5) self
				)
			)
			(3
				((ScriptID ULENCE 1)
					setLoop: 5
					setMotion: MoveTo 90 160 self
				)
			)
			(4
				((ScriptID ULENCE 1)
					posn: 81 168
					setLoop: 2
					setMotion: MoveTo 81 197 self
				)
			)
			(5
				(music fade:)
				((ScriptID ULENCE 6) fade:)
				((ScriptID ULENCE 1) hide:)
				(= seconds 3)
			)
			(6
				(if (!= (ulence status?) 6)
					(ulence status: 1 fieldOff: FALSE bikerComing: FALSE egoBusy: FALSE)
					(ego setScript: 0)
				)
				(narrator modNum: 611 say: 1 self)
			)
			(7 (HandsOn) (self dispose:))
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
					fade: 127 20 10 0
				)
				(= seconds 3)
			)
			(1
				((ScriptID ULENCE 6) init: play:)
				((ScriptID ULENCE 1) show: setMotion: MoveTo 122 171 self)
			)
			(2
				((ScriptID ULENCE 1)
					setLoop: 0
					setMotion: MoveTo 176 171 self
				)
			)
			(3
				(ulence status: 4)
				(HandsOn)
				((ScriptID ULENCE 1)
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo 341 (- (ego y?) 5) self
				)
			)
			(4
				(music fade:)
				((ScriptID ULENCE 6) fade:)
				((ScriptID ULENCE 1) hide:)
				(= seconds 3)
			)
			(5
				(if (!= (ulence status?) 6)
					(ulence status: 1 fieldOff: FALSE bikerComing: FALSE egoBusy: FALSE)
					(ego setScript: 0)
					(narrator modNum: 611 say: 1 self)
				)
				(= cycles 2)
			)
			(6 (HandsOn) (self dispose:))
		)
	)
)
