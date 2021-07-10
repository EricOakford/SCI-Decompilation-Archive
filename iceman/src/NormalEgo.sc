;;; Sierra Script 1.0 - (do not remove this comment)
(script# 823)
(include game.sh)
(use Main)
(use Motion)

(public
	NormalEgo 0
)

(procedure (NormalEgo theView theLoop)
	(if (>= argc 1)
		(ego view: theView)
		(if (>= argc 2)
			(ego loop: theLoop)
		)
	)
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		setStep: 3 2
		setCycle: Walk
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		ignoreActors: FALSE
		observeControl: cWHITE
	)
	(DisposeScript 823)
)
