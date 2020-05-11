;;; Sierra Script 1.0 - (do not remove this comment)
(script# END)
(include game.sh)
(use Sound)
(use Game)

(public
	RgEnd 0
	ENDMUSIC 1
)

(instance ENDMUSIC of Sound
	(properties)
)

(instance RgEnd of Region
	(properties
	;	name "Ending Region"
	)
	
	(method (init)
		(if initialized (return))
		(= keep TRUE)
		(super init:)
		(ENDMUSIC number: 201 owner: 521 init:)
	)
)
