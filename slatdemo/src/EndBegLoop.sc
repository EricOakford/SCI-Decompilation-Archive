;;; Sierra Script 1.0 - (do not remove this comment)
(script# ENDBEG)
(include game.sh)
(use Main)
(use Motion)


(class EndBegLoop of CycleTo
	(properties
		maxLoops 0
		lastView 0
		curLoop 0
		curView 1
		origView 0
	)
	
	(method (init actor theLastView)
		(= client actor)
		(= origView (client view?))
		(= curView (client view?))
		(= maxLoops (NumLoops client))
		(-- maxLoops)
		(if (>= argc 2)
			(= lastView theLastView)
		else
			(= lastView (client view?))
		)
		(super init: actor 100 1 &rest)
	)
	
	(method (doit &tmp newCel)
		(= newCel (self nextCel:))
		(client cel: newCel)
		(cond 
			((== cycleDir 1)
				(if (== (+ (client lastCel:) 1) newCel)
					(cond 
						((<= (++ curLoop) maxLoops)
							(client setLoop: curLoop cel: 0)
						)
						((<= (++ curView) lastView)
							(= curLoop 0)
							(client view: curView setLoop: 0 cel: 0)
							(= maxLoops (NumLoops client))
							(-- maxLoops)
						)
						(else
							(-- curLoop)
							(-- curView)
							(= cycleDir (- cycleDir))
							(client cel: (client lastCel:))
						)
					)
				)
			)
			((<= newCel 0)
				(cond 
					((>= (-- curLoop) 0)
						(client setLoop: curLoop)
						(client cel: (client lastCel:))
					)
					((>= (-- curView) origView)
						(client view: curView)
						(= maxLoops (NumLoops client))
						(-- maxLoops)
						(client
							setLoop: (= curLoop maxLoops)
							cel: (client lastCel:)
						)
					)
					(else (self cycleDone:))
				)
			)
		)
	)
	
	(method (cycleDone)
		(theGame handsOn:)
		(super cycleDone:)
	)
)
