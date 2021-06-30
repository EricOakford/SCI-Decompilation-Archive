;;; Sierra Script 1.0 - (do not remove this comment)
(script# 455)
(include game.sh)
(use Main)
(use LBRoom)
(use Inset)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm455 0
)

(instance rm455 of LBRoom
	(properties
		picture 555
	)
	
	(method (init)
		(LoadMany RES_VIEW 470 563)
		(LoadMany RES_SOUND 6)
		(super init:)
		(theGame points: 1 134)
		(self setScript: sFindPippin)
	)
	
	(method (dispose)
		(screamAndLook fade: 0 12 30 1)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (newRoom n)
		(InFirstPerson 0)
		(screamAndLook fade: 0 12 30 1)
		(wrapMusic dispose: 1)
		(super newRoom: n)
	)
)

(instance sFindPippin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 82 flags: 1 loop: 1 play:)
				(wrapMusic init: -1 1 6)
				(= seconds 3)
			)
			(1
				(theGame handsOn:)
				(InFirstPerson 1)
				(curRoom drawPic: 470)
				(curRoom picture: 470)
				(drip init: setCycle: Forward)
				(pipMummy init: setOnMeCheck: ftrControl cWHITE)
				(leftEye init: setOnMeCheck: 1 2)
				(rightEye init: setOnMeCheck: 1 4)
				(nose init: setOnMeCheck: 1 8)
				(moustache init: setOnMeCheck: 1 16)
				(mouthOpen init: setOnMeCheck: 1 32)
				(ear init: setOnMeCheck: 1 64)
				(hair init: setOnMeCheck: 1 128)
				(dagger init: setOnMeCheck: 1 256)
				(jacket init: setOnMeCheck: 1 512)
				(pants init: setOnMeCheck: 1 1024)
				(vest init: setOnMeCheck: 1 2048)
				(flesh init: setOnMeCheck: 1 4096)
				(shirt init: setOnMeCheck: 1 8192)
				(flower init: setOnMeCheck: 1 16384)
			)
			(2 (curRoom newRoom: 454))
		)
	)
)

(instance drip of Prop
	(properties
		x 180
		y 100
		noun 2
		view 470
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pipMummy of Feature
	(properties
		x 1
		y 1
		noun 13
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance leftEye of Feature
	(properties
		x 1
		y 1
		noun 14
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rightEye of Feature
	(properties
		x 1
		y 1
		noun 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance nose of Feature
	(properties
		x 1
		y 1
		noun 11
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance moustache of Feature
	(properties
		x 1
		y 1
		noun 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance mouthOpen of Feature
	(properties
		x 1
		y 1
		noun 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ear of Feature
	(properties
		x 1
		y 1
		noun 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hair of Feature
	(properties
		x 1
		y 1
		noun 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance dagger of Feature
	(properties
		x 1
		y 1
		noun 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance jacket of Feature
	(properties
		x 1
		y 1
		noun 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (ego has: 21)
					(messager say: 7 1 2)
				else
					(messager say: 7 1 1)
				)
			)
			(8
				(if (ego has: 21)
					(messager say: 7 8 2)
				else
					(messager say: 7 8 1)
				)
			)
			(4
				(if (ego has: 21)
					(messager say: 7 4 2)
				else
					(curRoom setInset: inNotepad)
				)
			)
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pants of Feature
	(properties
		x 1
		y 1
		noun 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vest of Feature
	(properties
		x 1
		y 1
		noun 16
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance flesh of Feature
	(properties
		x 1
		y 1
		noun 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shirt of Feature
	(properties
		x 1
		y 1
		noun 15
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance flower of Feature
	(properties
		x 1
		y 1
		noun 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (sFindPippin cue:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inNotepad of Inset
	(properties
		view 563
		loop 2
		cel 2
		x 118
		y 79
		disposeNotOnMe 1
	)
	
	(method (init)
		(super init: &rest)
		(messager say: 7 4 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(self dispose:)
				(theGame points: 1 135)
				((ScriptID 21 0) doit: 790)
				(ego get: 21)
			)
			(1
				(messager say: 45 1 0 0 0 15)
			)
			(8
				(messager say: 45 8 0 0 0 15)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wrapMusic of WrapMusic
	(properties)
	
	(method (init)
		(= wrapSound screamAndLook)
		(super init: &rest)
	)
)

(instance screamAndLook of Sound
	(properties)
)
