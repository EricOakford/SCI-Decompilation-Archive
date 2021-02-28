;;; Sierra Script 1.0 - (do not remove this comment)
(script# 241)
(include game.sh) (include "240.shm")
(use GloryTalker)
(use Actor)

(public
	aHarami 0
	haramiTalker 1
	guardTalker 2
)

(instance aHarami of Prop
	(properties
		x 137
		y 132
		noun N_HARAMI
		approachDist 35
		view 950
		loop 1
		cel 2
		signal ignrAct
		cycleSpeed 14
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_TALK V_FISH V_MEAT V_FRUIT V_RATIONS V_HONEY)
	)
)

(instance haramiTalker of GloryTalker
	(properties
		x 10
		y 10
		view 951
		loop 1
		talkWidth 260
		back 57
		textX 15
		textY 150
		backColor 26
	)
	
	(method (init)
		(super init: haramiBust haramiEyes haramiMouth &rest)
	)
)

(instance haramiBust of View
	(properties
		nsTop 20
		nsLeft 23
		view 951
		loop 3
	)
)

(instance haramiMouth of Prop
	(properties
		nsTop 45
		nsLeft 26
		view 951
	)
)

(instance haramiEyes of Prop
	(properties
		nsTop 30
		nsLeft 24
		view 951
		loop 2
	)
)

(instance guardTalker of GloryTalker
	(properties
		x 205
		y 2
		view 191
		loop 1
		talkWidth 260
		back 57
		textX -180
		textY 150
		backColor 14
	)
	
	(method (init)
		(super init: guardBust guardEyes guardMouth &rest)
	)
)

(instance guardMouth of Prop
	(properties
		nsTop 45
		nsLeft 43
		view 191
	)
)

(instance guardEyes of Prop
	(properties
		nsTop 35
		nsLeft 43
		view 191
		loop 2
	)
)

(instance guardBust of View
	(properties
		nsTop 24
		nsLeft 40
		view 191
		loop 3
	)
)
