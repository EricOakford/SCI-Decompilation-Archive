;;; Sierra Script 1.0 - (do not remove this comment)
(script# 86)
(include game.sh)
(use Main)
(use LoadMany)
(use Extra)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	titleScreen 0
	showTitle 1
)

(local
	i
	[spark 10]
	[sparkX 8] = [61 160 264 130 208 169 184 97]
	[sparkY 8] = [29 70 32 20 54 140 21 59]
	[sparkSpeed 8] = [1 3 0 2 1 0 2 1]
)
(procedure (AddSparks)
	(= i 0)
	(while (< i 8)
		((= [spark i] (Clone Extra))
			view: 950
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
		(++ i)
	)
)

(instance titleScreen of Room
	(properties
		picture 902
		style IRISOUT
	)
	
	(method (init)
		(HandsOff)
		(= global107 1)
		(Load SOUND 1)
		(LoadMany VIEW 913 950)
		(Load SCRIPT 87)
		(super init:)
		(TheMenuBar hide: state: FALSE)
		((ScriptID 0 23) number: 1 loop: 1 play:)
		(if (!= howFast 0) (AddSparks))
		(if (!= prevRoomNum 777)
			(showTitle start: 2)
			(self setScript: showTitle)
		else
			(QUEST init:)
			(FOR init:)
			(CROWN init:)
			(copyright init:)
			(self setScript: showTitle)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(super handleEvent: event)
		(if (== (event type?) mouseDown)
			(if (showTitle seconds?)
				(showTitle cue:)
			else
				(event claimed: TRUE)
			)
		)
		(if (== (event type?) keyDown)
			(switch (event message?)
				(ENTER
					(if (showTitle seconds?)
						(showTitle cue:)
					else
						(event claimed: TRUE)
					)
				)
				(`#2
					(TheMenuBar state: TRUE handleEvent: event state: 0)
				)
				(else  (event claimed: TRUE))
			)
		)
	)
)

(instance showTitle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (if (>= howFast 1) 10 else 5))
			)
			(1
				(= seconds 0)
				(QUEST dispose:)
				(FOR dispose:)
				(CROWN dispose:)
				(copyright dispose:)
				(= cycles 1)
			)
			(2
				(= global107 0)
				(if (!= howFast 0) ([spark 5] hide:))
				(self setScript: (ScriptID 87 0))
			)
			(3
				(if (!= howFast 0)
					(= i 0)
					(while (< i 8)
						([spark i] dispose:)
						(++ i)
					)
				)
				((ScriptID 0 23) fade:)
				(= cycles 2)
			)
			(4
				((ScriptID 87 0) changeState: 3)
			)
		)
	)
)

(instance QUEST of View
	(properties
		x 67
		y 167
		view 913
	)
)

(instance FOR of View
	(properties
		x 133
		y 169
		view 913
		cel 1
	)
)

(instance CROWN of View
	(properties
		x 226
		y 164
		view 913
		cel 2
	)
)

(instance copyright of View
	(properties
		x 164
		y 181
		view 913
		loop 1
	)
)
