;;; Sierra Script 1.0 - (do not remove this comment)
(script# dmBegin)
(include game.sh)
(use Main)
(use LoadMany)
(use Extra)
(use Game)
(use Actor)
(use System)

(public
	titleScreen 0
)

(local
	i
	[aSpark 10]
	sparkX = [61 160 264 130 208 169 184 97]
	sparkY = [29 70 32 20 54 140 21 59]
	sparkSpeed = [1 3 0 2 1 0 2 1]
)
(procedure (addSpark)
	(for ((= i 0)) (< i 8) ((++ i))
		((= [aSpark i] (Clone spark))
			view: vSpark
			setLoop: 0
			cel: 5
			posn: [sparkX i] [sparkY i]
			cycleSpeed: [sparkSpeed i]
			cycleType: 2
			pauseCel: 5
			hesitation: (Random 0 10)
			ignoreActors:
			isExtra: TRUE
			init:
		)
	)
)

(instance titleScreen of Room
	(properties
		picture pTitle
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW vTitle vSpark)
		(super init:)
		(addToPics
			add: QUEST FOR CROWN copyright
			eachElementDo: #init
			doit:
		)
		(if (!= howFast slow)
			(addSpark)
		)
		(self setScript: showTitle)
	)
	
	(method (dispose)
		(if cast
			(cast eachElementDo: #dispose)
		)
		(super dispose:)
	)
)

(instance showTitle of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 6) number: 1 loop: -1 play:)
				(= i 0)
				(= seconds 10)
			)
			(1
				(if (!= howFast slow)
					([aSpark i] dispose:)
					(= cycles 1)
				else
					(self cue:)
				)
			)
			(2
				(if (< (++ i) 8)
					(self changeState: 1)
				else
					(self cue:)
				)
			)
			(3
				(curRoom newRoom: rOldRm1)
			)
		)
	)
)

(instance QUEST of View
	(properties
		x 67
		y 167
		view vTitle
		signal fixedLoop
	)
)

(instance FOR of View
	(properties
		x 133
		y 169
		view vTitle
		cel 1
		signal fixedLoop
	)
)

(instance CROWN of View
	(properties
		x 226
		y 164
		view vTitle
		cel 2
		signal fixedLoop
	)
)

(instance copyright of View
	(properties
		x 164
		y 181
		view vTitle
		loop 1
	)
)

(instance spark of Extra)
