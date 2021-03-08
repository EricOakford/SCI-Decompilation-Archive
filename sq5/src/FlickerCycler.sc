;;; Sierra Script 1.0 - (do not remove this comment)
(script# FLICKER)
(include game.sh)
(use Main)
(use Motion)


(class FlickerCycler of Cycle
	(properties
		cycleSpeed 8
	)
	
	(method (doit &tmp temp0)
		(self nextCel:)
	)
	
	(method (nextCel)
		(if (< (Abs (- gameTime cycleCnt)) cycleSpeed)
			(client cel?)
		else
			(= cycleSpeed (Random 5 30))
			(client cel?)
			(= cycleCnt gameTime)
			(if (client isNotHidden:)
				(client hide:)
			else
				(client show:)
			)
		)
	)
)
