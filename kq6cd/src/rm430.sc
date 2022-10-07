;;; Sierra Script 1.0 - (do not remove this comment)
(script# 430)
(include sci.sh)
(use Main)
(use rLab)
(use n401)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	rm430 0
)

(instance rm430 of LabRoom
	(properties
		east 400
	)
	
	(method (init)
		(proc401_3)
		(super init: &rest)
		(theCorpseNorth init: stopUpd:)
		(if (== ((inventory at: 7) owner?) curRoomNum)
			(theGlint init:)
		)
		(curRoom setScript: (ScriptID 30 1))
		((ScriptID 30 0) initCrypt: 4)
		((ScriptID 30 6) addToPic:)
		((ScriptID 30 10) addToPic:)
		((ScriptID 30 8) addToPic:)
	)
	
	(method (notify)
		((ScriptID 30 6) addToPic:)
		((ScriptID 30 10) addToPic:)
		((ScriptID 30 8) addToPic:)
		((ScriptID 30 3) show:)
	)
)

(instance theGlint of Prop
	(properties
		x 132
		y 111
		modNum 400
		view 902
		priority 9
		signal $4010
		cycleSpeed 3
	)
	
	(method (init)
		(theDeadMansCoin init:)
		(self setScript: glintCoinEyes)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp theScript)
		(switch theVerb
			(5 (ego setScript: getCoins))
			(else 
				(= theScript (ScriptID 30 4))
				(theScript doVerb: theVerb &rest)
			)
		)
	)
)

(instance theDeadMansCoin of Prop
	(properties
		x 133
		y 170
		z 58
		noun 13
		modNum 400
		view 431
		loop 6
		priority 8
		signal $4010
		cycleSpeed 15
	)
	
	(method (doVerb theVerb &tmp theScript)
		(switch theVerb
			(5 (ego setScript: getCoins))
			(1
				(messager say: 13 1 0 0 0 400)
			)
			(else 
				(= theScript (ScriptID 30 4))
				(theScript doVerb: theVerb &rest)
			)
		)
	)
)

(instance theCorpseNorth of View
	(properties
		x 132
		y 122
		z 10
		approachX 132
		approachY 112
		view 400
		loop 1
		cel 2
		priority 7
		signal $4010
	)
	
	(method (init)
		(if (cast contains: theDeadMansCoin)
			(self noun: 13)
		else
			(self noun: 8)
		)
		(super init:)
	)
	
	(method (doVerb theVerb &tmp theScript)
		(switch theVerb
			(7
				(messager say: 13 7 0 1 0 400)
			)
			(5
				(if (cast contains: theDeadMansCoin)
					(ego setScript: getCoins)
				else
					(curRoom setScript: (ScriptID 30 11) 0 self)
				)
			)
			(else 
				(if (cast contains: theDeadMansCoin)
					(theDeadMansCoin doVerb: theVerb &rest)
				else
					(= theScript (ScriptID 30 4))
					(theScript doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance getCoins of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 127 149 self)
			)
			(1
				(ego
					view: 431
					normal: 0
					setLoop: 5
					cel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(2
				(ego setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(if (!= ((inventory at: 7) owner?) curRoomNum)
					(messager say: 8 5 0 1 self 400)
				else
					(messager say: 13 5 0 1 self 400)
				)
			)
			(5
				(ego reset: 3)
				(if (== ((inventory at: 7) owner?) curRoomNum)
					(theGame givePoints: 1)
					(ego get: 7)
					(theDeadMansCoin dispose:)
					(theGlint dispose:)
				)
				(= seconds 1)
			)
			(6
				(theGame handsOn:)
				(ego setHeading: 180)
				(theCorpseNorth noun: 8)
				(self dispose:)
			)
		)
	)
)

(instance glintCoinEyes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGlint setCycle: BegLoop self)
			)
			(1
				(theGlint hide:)
				(= seconds 10)
			)
			(2
				(= state (- state 3))
				(theGlint show:)
				(self cue:)
			)
		)
	)
)
