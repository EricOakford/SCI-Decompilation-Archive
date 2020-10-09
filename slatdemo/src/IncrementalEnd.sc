;;; Sierra Script 1.0 - (do not remove this comment)
(script# INCEND)
(include game.sh)
(use Main)
(use Motion)


(class IncrementalEnd of EndLoop
	(properties
		maxLoops 0
		lastView 0
		curLoop 0
		curView 0
		origView 0
		origLoop 0
		origCel 0
	)
	
	(method (init actor theLastView)
		(= client actor)
		(= origCel (client cel?))
		(= origView (client view?))
		(= origLoop (client loop?))
		(= curView (client view?))
		(= maxLoops (NumLoops client))
		(-- maxLoops)
		(if (>= argc 2)
			(= lastView theLastView)
		else
			(= lastView (client view?))
		)
		(super init: actor actor &rest)
	)
	
	(method (doit &tmp newCel)
		(= newCel (self nextCel:))
		(client cel: newCel)
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
					(client cel: (client lastCel:))
					(self cycleDone:)
				)
			)
		)
	)
	
	(method (cycleDone)
		(theGame handsOn:)
		(client view: origView loop: origLoop cel: origCel)
		(super cycleDone:)
	)
)
