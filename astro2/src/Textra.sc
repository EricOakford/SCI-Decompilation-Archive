;;; Sierra Script 1.0 - (do not remove this comment)
(script# TEXTRA)
(include game.sh)
(use Main)
(use Extra)


;;;(procedure
;;;	InitSurrogate
;;;)

(class TalkingExtra of Extra
	
	(properties
		surrogate 0
		initialized	FALSE
	)
	
	(method (init theSurrogate)
		(if argc
			(= surrogate theSurrogate)
		)
		(super init:)
		(self isExtra:TRUE)
	); init
	
	(method (dispose)
		;;someone has to dispose our surrogate if we never addToPic'd
		(if (and surrogate (not (features contains: surrogate)))
			(surrogate dispose:)
		)
		(super dispose:)
	);dispose
	
	(method (addToPic)
		(if surrogate 
			(if (not initialized)
				(InitSurrogate self surrogate)
			)
			;(surrogate init)
			(features add: surrogate)
		)
		(super addToPic: &rest)
	); addToPic
	
	(method (handleEvent)
		(if surrogate
			(if (not initialized)
				(InitSurrogate self surrogate)
			)	
			(surrogate handleEvent: &rest)
		else
			(super handleEvent: &rest)
		)
	); handleEvent
); TalkingExtra


(procedure (InitSurrogate e s)
	(e initialized:TRUE)
	(s
		x:	(e x?)
		y:	(e y?)	
			
	)
	(if (s respondsTo:#nsRight)
		(s
			nsRight: 	(e nsRight?)	,
			nsLeft: 	(e nsLeft?)	,
			nsBottom: (e nsBottom?),
			nsTop: 	(e nsTop?)	
		)
	)
);InitSurrogate
