;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	TUTORIAL.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Brian K. Hughes
;;;;	Updated:	
;;;;
;;;;	This class is designed for in-game tutorials
;;;;
;;;;	Classes:
;;;;		Tutorial


(script# TUTORIAL)
(include game.sh)
(use Main)
(use Print)
(use System)


(class Tutorial kindof Script
	(properties
		nextItem				NULL
		nextAction			NULL
		numTries				NULL
	)
;;;	(methods
;;;		waitFor
;;;		report
;;;	)

	(method (waitFor obj act nOrMsg v c s m)
		;
		; Set up the conditions by which we will be cued next, where
		;	'obj' is the object to watch for and 'act' is the action
		;	required to trigger the object.  If 'act' is NULL, any
		;	action will trigger.

		(= nextItem obj)
		(= nextAction act)
		(cond
			((== argc 3)
				(Prints nOrMsg)
			)
			((> argc 3)
				(messager say: nOrMsg v c s m)
			)
		)
	)

	(method (report objOrVerb)
		;
		; Report a wrong action (negative reinforcement, bad!)
		;	'objOrVerb' will be the object ID (if wrong item) or verb
		;	number (if wrong action)
	)

	(method (cue)
		(= numTries 0)
		(super cue: &rest)
	)
)
