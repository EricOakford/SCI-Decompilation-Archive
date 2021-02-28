;;; Sierra Script 1.0 - (do not remove this comment)
(script# HARAMI_TALKER)
(include game.sh) (include "40.shm")
(use Main)
(use Target)
(use GloryTalker)
(use Actor)
(use System)

(public
	haramiATalker 0
	Harami 1
)

(instance Harami of TargActor
	(properties
		noun N_HARAMI
		view 950
	)
	
	(method (doVerb theVerb &tmp what)
		(if (OneOf theVerb V_RATIONS V_FRUIT V_HONEY V_MEAT V_FISH)
			(= what
				(switch theVerb
					(V_RATIONS iRations)
					(V_FRUIT iFruit)
					(V_HONEY iHoney)
					(V_MEAT iMeat)
					(V_FISH iFish)
				)
			)
			(ego drop: what 1)
			(Bset fFedHarami)
			(ego addHonor: 10)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance haramiATalker of GloryTalker
	(properties
		x 185
		y 10
		view 951
		loop 1
		talkWidth 260
		back 57
		textX -161
		textY 100
		backColor 26
	)
	
	(method (init)
		(super init: haramiABust haramiAEyes haramiAMouth &rest)
	)
)

(instance haramiAMouth of Prop
	(properties
		nsTop 45
		nsLeft 26
		view 951
	)
)

(instance haramiAEyes of Prop
	(properties
		nsTop 30
		nsLeft 24
		view 951
		loop 2
	)
)

(instance haramiABust of View
	(properties
		nsTop 20
		nsLeft 23
		view 951
		loop 3
	)
)
