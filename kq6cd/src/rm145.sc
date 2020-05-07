;;; Sierra Script 1.0 - (do not remove this comment)
(script# 145)
(include sci.sh)
(use Main)
(use NewRoomCue)
(use Kq6Procs)
(use Conv)
(use TimedCue)
(use Motion)
(use Actor)
(use System)

(public
	rm145 0
)

(local
	local0
)
(instance rm145 of KQ6Room
	(properties
		picture 98
		style $0009
	)
	
	(method (init)
		(theIconBar disable:)
		(super init: &rest)
		(curRoom setScript: seqScr)
	)
	
	(method (dispose)
		(theIconBar enable:)
		(Bclr 133)
		(DisposeScript 960)
		(super dispose:)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance eyeCue of TimedCue
	(properties
		register 1
	)
	
	(method (init param1 theTicks)
		(super init: param1 0 0)
		(= ticks theTicks)
	)
)

(class GlintingEye of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 902
		loop 0
		cel 0
		priority 15
		underBits 0
		signal $4010
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		caller 0
	)
	
	(method (init theCaller)
		(if argc (= caller theCaller))
		(super init: &rest)
		(self setCycle: End self)
	)
	
	(method (cue)
		(if (not argc)
			(self setScript: eyeCue 12)
		else
			(if caller (caller cue:))
			(self dispose:)
		)
	)
)

(instance hiEye1 of GlintingEye
	(properties
		x 188
		y 61
	)
)

(instance lowEye of GlintingEye
	(properties
		x 179
		y 126
	)
)

(instance hiEye2 of GlintingEye
	(properties
		x 180
		y 62
	)
)

(instance seqScr of Script
	(properties)
	
	(method (dispose)
		(theMusic fade:)
		(super dispose:)
		(genie dispose:)
		(vizier dispose:)
		(if (cast contains: bottle) (bottle dispose:))
		(curRoom drawPic: 98 9)
		(curRoom newRoom: 280)
	)
	
	(method (changeState newState &tmp temp0 [temp1 100])
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(curRoom
					drawPic: 98 (if (or (== howFast 0) global169) 9 else 12)
				)
				(if (Btst 41)
					(theMusic number: 150)
					(= local0 1)
					(= register 3)
					(Bclr 41)
				else
					(theMusic number: 145)
					(if (Btst 72) (= register 1) else (= register 2))
					(Bset 41)
				)
				(theMusic loop: -1 play:)
				(Message msgGET 145 1 0 4 1 @temp1)
				(Display @temp1 dsCOORD 82 85 dsCOLOR 14 dsFONT 0)
				(= seconds 5)
			)
			(2
				(if local0 (bottle init:))
				(genie init:)
				(vizier init:)
				(curRoom
					drawPic: 145 (if (or (== howFast 0) global169) 9 else 11)
				)
				(= cycles 1)
			)
			(3
				(cond 
					((== register 3)
						(roomConv
							add: -1 1 0 3 1
							add: eyeGlintScr
							add: -1 1 0 3 2
							add: eyeGlintScr
							add: -1 1 0 3 3
							add: eyeGlintScr
							add: -1 1 0 3 4
							add: eyeGlintScr
							add: -1 1 0 3 5
							add: eyeGlintScr
							add: -1 1 0 3 6
							add: eyeGlintScr
							add: -1 1 0 3 7
							add: eyeGlintScr
							add: -1 1 0 3 8
							add: eyeGlintScr
							add: -1 1 0 3 9
							add: eyeGlintScr
							add: -1 1 0 3 10
							add: genieBottleScr
							init: self
						)
						(++ state)
					)
					((== register 1)
						(roomConv
							add: -1 1 0 1 1
							add: genieFallScr
							add: -1 1 0 1 2
							add: genieFallScr
							add: -1 1 0 1 3
							add: eyeGlintScr
							add: -1 1 0 1 4
							add: eyeGlintScr
							add: -1 1 0 1 5
							add: eyeGlintScr
							add: -1 1 0 1 6
							add: eyeGlintScr
							add: -1 1 0 1 7
							init: self
						)
						(++ state)
					)
					(else
						(roomConv
							add: -1 1 0 2 1
							add: genieFallScr
							add: -1 1 0 2 2
							add: genieFallScr
							add: -1 1 0 2 3
							add: eyeGlintScr
							add: -1 1 0 2 4
							add: eyeGlintScr
							add: -1 1 0 2 5
							add: eyeGlintScr
							add: -1 1 0 2 6
							add: eyeGlintScr
							init: self
						)
					)
				)
			)
			(4
				(roomConv
					add: -1 1 0 2 7
					add: eyeGlintScr
					add: -1 1 0 2 8
					add: eyeGlintScr
					add: -1 1 0 2 9
					add: eyeGlintScr
					add: -1 1 0 2 10
					add: eyeGlintScr
					add: -1 1 0 2 11
					add: eyeGlintScr
					add: -1 1 0 2 12
					add: eyeGlintScr
					add: -1 1 0 2 13
					init: self
				)
			)
			(5 (self dispose:))
		)
	)
)

(instance vizier of Prop
	(properties
		x 62
		y 115
		view 1464
		signal $0001
	)
)

(instance genie of Prop
	(properties
		x 157
		y 139
		view 1461
		signal $0001
	)
)

(instance genieFallScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(= start (+ state 1))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 133)
				(DisposeScript 1013)
				(genie
					view: 1461
					loop: 1
					cel: 0
					cycleSpeed: 15
					setCycle: End self
				)
			)
			(1
				(theMusic pause:)
				(theGlobalSound number: 147 loop: 1 play:)
				(vizier cycleSpeed: 15 setCycle: End)
				(genie view: 1462 loop: 0 cel: 0 setCycle: End self)
			)
			(2
				(theGlobalSound number: 960 loop: 1 play:)
				(genie hide:)
				(vizier setCycle: Beg)
				(ScriptID 1013)
				(= seconds 3)
			)
			(3 (self dispose:))
			(4
				(genie
					show:
					view: 1463
					cel: 0
					x: 205
					y: 137
					setCycle: End self
				)
			)
			(5
				(theMusic pause: 0 setVol: 0 fade: 127 10 15 0)
				(genie stopUpd:)
				(vizier stopUpd:)
				(= cycles 2)
			)
			(6 (self dispose:))
		)
	)
)

(instance genieBottleScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGlobalSound number: 943 loop: 1 play:)
				(genie view: 1465 posn: 186 99 loop: 0 cycleSpeed: 8)
				(if register
					(genie cel: 6 setCycle: Beg self)
				else
					(genie cel: 0 setCycle: End self)
				)
			)
			(1 (= ticks 60))
			(2 (self dispose:))
		)
	)
)

(instance eyeGlintScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (Random 0 1) (cast contains: genie))
					(cond 
						((Btst 133) (lowEye init: self))
						((Random 0 1) (hiEye1 init: self))
						(else (hiEye2 init: self))
					)
				else
					(self dispose:)
				)
			)
			(1 (= cycles 2))
			(2 (self dispose:))
		)
	)
)

(instance bottle of View
	(properties
		x 218
		y 151
		view 1465
		loop 1
	)
)
