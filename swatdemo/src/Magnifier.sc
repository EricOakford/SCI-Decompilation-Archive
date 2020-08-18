;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	MAGNIFY.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author:  Brian K. Hughes
;;;;	Updated:
;;;;
;;;;	The Magnifier class provides view magnification effects.
;;;;
;;;;	Classes:
;;;;		Magnifier


(script# MAGNIFIER)
(include game.sh)
(use System)


(class Magnifier kindof Object
	;
	; A Magnifier is an object that contains data about a bitmap to be
	;	magnified.  A View owns a Magnifier, and the Magnifier must be
	;	registered with the magnification list in the interpreter via
	;	the setMagnifier method of View.

	(properties
		x					0
		y					0
		view				-1		;\
		loop				0		; > background bitmap to be magnified
		cel				0		;/
		magPower			0		;magnification power, in 'n':1
		skip				0		;skip color in client that will show through
		client			0		;owner view
	)
	
	(method (init who v l c thePower theSkip)
		;
		; Setup the Magnifier

		(= client who)
		(if (> argc 1)
			(= view v)
			(if (> argc 2)
				(= loop l)
				(if (> argc 3)
					(= cel c)
					(if (> argc 4)
						(= magPower thePower)
						(if (> argc 5)
							(= skip theSkip)
						)
					)
				)
			)
		)
		(AddMagnify self)
	)

	(method (dispose)
		(DeleteMagnify self)
		(client magnifier: 0)
		(= client 0)
		(super dispose:)
	)
)