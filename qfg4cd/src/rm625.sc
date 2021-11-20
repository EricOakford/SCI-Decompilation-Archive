;;; Sierra Script 1.0 - (do not remove this comment)
(script# 625)
(include sci.sh)
(use Main)
(use GloryRm)
(use Scaler)
(use Osc)
(use PolyPath)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm625 0
)

(local
	local0
	local1
	local2
	local3
)
(instance rm625 of GloryRm
	(properties
		modNum 620
		picture 620
		north 643
	)
	
	(method (init)
		(RemapColors 2 253 140)
		(RemapColors 2 254 60)
		(switch prevRoomNum
			(643 (ego posn: 88 32))
			(630 (ego posn: 276 152))
			(else  (ego posn: 276 152))
		)
		(ego init: setScaler: Scaler 105 32 180 31 normalize:)
		((ScriptID 633 0) init:)
		(super init: &rest)
		(theGhost init: setScaler: Scaler 103 43 117 33 hide:)
		(theGame handsOn:)
		(theGhost setScript: doMyThing)
	)
	
	(method (dispose)
		(if script (script dispose:))
		((ScriptID 633 3) stop: clean: (ScriptID 633 3))
		(DisposeScript 633)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((== theVerb 1) (messager say: 0 1 0 0 0 625))
				((== theVerb 86) (= local3 3) (curRoom setScript: sThrowIt))
				((== theVerb 93) (= local3 4) (curRoom setScript: sThrowIt))
				((== theVerb 88) (= local3 5) (curRoom setScript: sThrowIt))
				((== theVerb 79) (= local3 6) (curRoom setScript: sThrowIt))
				((== theVerb 21)
					(ego use: 6 1)
					(= local3 2)
					(curRoom setScript: sThrowIt)
				)
				((== theVerb 83)
					(Palette palSET_FLAG 0 255 500)
					(Palette palSET_FLAG 0 255 100)
					(messager say: 4 6 28 1 0 620)
					(return 1)
				)
				((== theVerb 37)
					(if (== (ego has: 5) 1)
						(messager say: 4 6 34)
					else
						(ego use: 5 1)
						(= local3 1)
						(curRoom setScript: sThrowIt)
					)
				)
				((== theVerb 36) (curRoom setScript: sSlash))
				(else (super doVerb: theVerb))
			)
		)
	)
)

(instance sSlash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (theGhost x?) (theGhost y?) self
				)
			)
			(1 (Face ego theGhost self))
			(2
				(ego
					view: 38
					setLoop: 1
					setCel: 0
					posn: (- (ego x?) 1) (+ (ego y?) 2)
					setCycle: End self
				)
			)
			(3
				(sndSwish play:)
				(ego
					setLoop: 5
					setCel: 0
					x: (+ (ego x?) 2)
					setCycle: Osc 4 self
				)
			)
			(4
				(ego setCel: 8 setCycle: Beg self)
			)
			(5
				(sndSwish stop:)
				(ego
					setLoop: 1
					setCel: 10
					x: (- (ego x?) 2)
					setCycle: Beg self
				)
			)
			(6
				(ego posn: (+ (ego x?) 1) (- (ego y?) 2) normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sThrowIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch local3
					(1
						(if (== (ego has: 5) 1)
							(messager say: 4 6 34)
						else
							(self setScript: (ScriptID 32) self 37)
						)
					)
					(2
						(self setScript: (ScriptID 32) self 21)
					)
					(3
						(self setScript: (ScriptID 32) self 86)
					)
					(4
						(self setScript: (ScriptID 32) self 93)
					)
					(5
						(self setScript: (ScriptID 32) self 88)
					)
					(6
						(self setScript: (ScriptID 32) self 79)
					)
				)
			)
			(1 (= seconds 8))
			(2
				(ego normalize:)
				(messager say: 4 6 28 1 self 620)
			)
			(3
				(= local3 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance doMyThing of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (<= (ego distanceTo: theGhost) 30) (not local2))
			(messager say: 4 6 8 0 0 620)
			(= local2 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(if (and (== heroType 3) (not local1))
					(= local1 1)
					(messager say: 4 6 7 0 self 620)
				else
					(self cue:)
				)
			)
			(2
				(sndEerie play:)
				(theGhost
					posn: 81 35
					setPri: 238
					show:
					ignoreActors: 1
					setCycle: Fwd
					cycleSpeed: 12
					setMotion: MoveTo 57 61 self
				)
			)
			(3
				(sndEerie stop:)
				(theGhost setMotion: MoveTo 78 107 self)
			)
			(4
				(theGhost setMotion: MoveTo 161 142 self)
			)
			(5
				(theGhost setMotion: MoveTo 335 189 self)
			)
			(6
				(theGhost hide:)
				(= local0 (Random 1 2))
				(= seconds (Random 20 30))
			)
			(7
				(= local2 0)
				(self changeState: 0)
			)
		)
	)
)

(instance aZap of Actor
	(properties
		x 79
		y 166
		view 31
		loop 2
		signal $4001
	)
)

(instance theGhost of Actor
	(properties
		noun 9
		modNum 620
		priority 238
		fixPriority 1
		view 845
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((== theVerb 86) (= local3 3) (curRoom setScript: sThrowIt))
				((== theVerb 93) (= local3 4) (curRoom setScript: sThrowIt))
				((== theVerb 88) (= local3 5) (curRoom setScript: sThrowIt))
				((== theVerb 79) (= local3 6) (curRoom setScript: sThrowIt))
				((== theVerb 21)
					(ego use: 6 1)
					(= local3 2)
					(curRoom setScript: sThrowIt)
				)
				((== theVerb 83)
					(Palette palSET_FLAG 0 255 500)
					(Palette palSET_FLAG 0 255 100)
					(messager say: 4 6 28 1 0 620)
					(return 1)
				)
				((== theVerb 37)
					(if (== (ego has: 5) 1)
						(messager say: 4 6 34)
					else
						(ego use: 5 1)
						(= local3 1)
						(curRoom setScript: sThrowIt)
					)
				)
				((== theVerb 36) (curRoom setScript: sSlash))
				(else (super doVerb: theVerb))
			)
		)
	)
)

(instance sndSwish of Sound
	(properties
		number 967
		loop -1
	)
)

(instance sndEerie of Sound
	(properties
		number 612
	)
)
