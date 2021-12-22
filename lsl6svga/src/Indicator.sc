;;; Sierra Script 1.0 - (do not remove this comment)
(script# 88)
(include sci.sh)
(use Main)
(use String)
(use Actor)
(use System)

(public
	PointGauge 0
)

(procedure (localproc_045f param1 param2 &tmp temp0)
	(= temp0 (if (== param2 -1) 0 else param2))
	(switch param1
		(0
			(UpdateScreenItem
				(indicator1 loop: temp0 cel: 0 yourself:)
			)
		)
		(1
			(UpdateScreenItem
				(indicator2 loop: temp0 cel: 0 yourself:)
			)
		)
		(2
			(UpdateScreenItem
				(indicator3 loop: temp0 cel: 0 yourself:)
			)
		)
	)
)

(class Indicator of Prop
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		dir 1
		active 0
		caller 0
		lastTicks 0
		ticks 0
	)
	
	(method (doit)
		(if
			(<=
				(= ticks (- ticks (Abs (- gameTime lastTicks))))
				0
			)
			(= ticks cycleSpeed)
			(cond 
				((and (> dir 0) (<= cel 15))
					(self show:)
					(++ cel)
					(if (> cel 15) (if caller (caller cue:)) (= active 0))
					(super doit:)
				)
				((and (< dir 0) (> cel 0)) (-- cel) (self show:) (super doit:))
				(else (if caller (caller cue:)) (= active 0) (super doit:))
			)
		)
		(= lastTicks gameTime)
	)
	
	(method (cue)
		(cond 
			((and (== dir 1) (== loop 9)) (= cel (= loop 0)))
			((== dir 1) (++ loop) (= cel 0))
			((== loop 0) (= loop 9) (= cel 15))
			(else (-- loop) (= cel 15))
		)
	)
	
	(method (advance theDir theCaller)
		(= lastTicks gameTime)
		(= ticks 1)
		(if argc (= caller theCaller))
		(= active 1)
		(if (== (= dir theDir) -1) (self cue:))
	)
	
	(method (stop)
		(if active (= active 0) (self cue:))
	)
)

(instance indicator1 of Indicator
	(properties
		x 188
		y 3
		priority 200
		fixPriority 1
		view 9721
		cycleSpeed 3
	)
)

(instance indicator2 of Indicator
	(properties
		x 182
		y 3
		priority 200
		fixPriority 1
		view 9721
		cycleSpeed 3
	)
)

(instance indicator3 of Indicator
	(properties
		x 176
		y 3
		priority 200
		fixPriority 1
		view 9721
		cycleSpeed 3
	)
)

(class PointGauge of Code
	(properties
		scratch 0
		currentValue 0
		running 0
	)
	
	(method (init &tmp temp0)
		(indicator1 init: (ScriptID 0 1))
		(indicator2 init: (ScriptID 0 1))
		(indicator3 init: (ScriptID 0 1))
		(= running 1)
	)
	
	(method (doit)
		(if (not (theGame controlsVisible?)) (return))
		(if (and (!= score currentValue) (not running))
			(cond 
				((> score currentValue) (self advance:))
				((< score currentValue) (self advance: 0))
			)
		)
		(if (indicator1 active?) (indicator1 doit:))
		(if (indicator2 active?) (indicator2 doit:))
		(if (indicator3 active?) (indicator3 doit:))
	)
	
	(method (dispose)
		(indicator1 dispose:)
		(indicator2 dispose:)
		(indicator3 dispose:)
		(= running 0)
		(super dispose: &rest)
	)
	
	(method (show)
		(if (not (indicator1 plane?)) (self init:))
		(indicator1 show:)
		(indicator2 show:)
		(indicator3 show:)
		(self update:)
		(= running 0)
	)
	
	(method (advance param1 &tmp temp0 temp1 indicator2Loop)
		(= running 1)
		(if (or (not argc) param1)
			(++ currentValue)
			(= temp0 9)
			(= temp1 1)
		else
			(-- currentValue)
			(= temp0 0)
			(= temp1 -1)
		)
		(if (== (indicator1 loop?) temp0)
			(= indicator2Loop (indicator2 loop?))
			(indicator2
				advance: temp1 (if (!= temp1 -1) indicator2 else 0)
			)
			(if (== indicator2Loop temp0)
				(indicator3
					advance: temp1 (if (!= temp1 -1) indicator3 else 0)
				)
			)
		)
		(indicator1 advance: temp1 self)
	)
	
	(method (cue &tmp temp0)
		(if (!= (indicator1 dir?) -1) (indicator1 cue:))
		(= running 0)
	)
	
	(method (hide)
		(= running 1)
		(indicator1 hide:)
		(indicator2 hide:)
		(indicator3 hide:)
	)
	
	(method (handleEvent)
		(return 0)
	)
	
	(method (update &tmp temp0 temp1 temp2 temp3)
		(indicator1 active: 0)
		(indicator2 active: 0)
		(indicator3 active: 0)
		(= temp1 ((= temp0 (Str format: {%d} score)) size:))
		(= temp2 0)
		(while (< temp2 temp1)
			(= temp3 (- (temp0 at: (- (- temp1 temp2) 1)) 48))
			(localproc_045f temp2 temp3)
			(++ temp2)
		)
		(= temp2 0)
		(while (< temp2 (- 3 (- temp1 1)))
			(localproc_045f (- 3 temp2) -1)
			(++ temp2)
		)
		(= currentValue score)
		(temp0 dispose:)
	)
)
