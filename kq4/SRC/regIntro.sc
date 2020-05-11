;;; Sierra Script 1.0 - (do not remove this comment)
(script# INTRO)
(include game.sh)
(use Sound)
(use Game)

(public
	kq4Intro 0
	KQ4MUSIC 1
)

(instance KQ4MUSIC of Sound
	(properties
		number 104
	)
)

(instance kq4Intro of Region
	(properties
	;	name "intro Region"
	)
	
	(method (init)
		(if initialized (return))
		(= keep TRUE)
		(super init:)
		((ScriptID INTRO 1) owner: kq4Intro init:)
	)
)
