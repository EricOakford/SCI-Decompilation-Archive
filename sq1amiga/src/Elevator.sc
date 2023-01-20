;;; Sierra Script 1.0 - (do not remove this comment)
(script# 817)
(include sci.sh)
(use Main)
(use Intrface)
(use Feature)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	Elevator 0
)

(class Elevator of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		cycleSpeed 8
		script 0
		cycler 0
		timer 0
		detailLevel 0
		whereTo 0
		level 0
		busy 0
		light 0
		locked 0
		exiting 0
		openSnd 315
		closeSnd 311
		polyCode 0
		unlockScript 0
		exitScript 0
		lockStr 0
		state $0000
		pauseCounter 0
		moveToX 0
		moveToY 0
		rectT 0
		rectL 0
		rectB 0
		rectR 0
	)
	
	(method (init &tmp temp0)
		(super init: &rest)
		(if (== level currentFloor)
			(if (== curRoomNum 10)
				(self approachVerbs: 3 4)
			else
				(self approachVerbs: 3)
			)
		else
			(self _approachVerbs: 26505)
		)
		(if light
			(light
				init:
				ignoreActors:
				setPri: 14
				posn: x (- y (+ (CelHigh view loop cel) 2))
			)
		)
		(= temp0 (CelWide view loop cel))
		(= rectT y)
		(= rectL (- x (- (/ temp0 2) 3)))
		(= rectR (+ x (- (/ temp0 2) 3)))
		(= rectB (+ y 10))
		(= approachX x)
		(= approachY (+ y (/ (- rectB y) 2)))
		(self stopUpd: moveToX: x moveToY: (- y 7))
		(if exiting
			(= busy 1)
			(ego posn: moveToX moveToY setHeading: 180)
			(self open:)
		else
			(self ignoreActors: 0)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (== state 6)
			(if exiting
				(if
				(== (++ pauseCounter) (if (>= howFast 1) 40 else 20))
					(= pauseCounter 0)
					(self cue:)
				)
			)
			(if (not exiting) (self cue:))
		)
	)
	
	(method (dispose)
		(if light (light dispose:))
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(if (ego inRect: rectL rectT rectR rectB)
					(HandsOff)
					(self open:)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(4
				(if locked
					(if (== theItem 1)
						(cond 
							(unlockScript
								(= locked 0)
								(= state 5)
								(curRoom setScript: unlockScript self)
							)
							(species (= locked 0) (HandsOff) (self open:))
						)
					else
						(super doVerb: theVerb theItem &rest)
					)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
		(CueObj
			state: 0
			cycles: 0
			client: 0
			theVerb: 0
			theInvItem: 0
		)
	)
	
	(method (cue)
		(switch state
			(5 (self open:))
			(6
				(= state 4)
				(ego setMotion: 0)
				(if (and light (not exiting))
					(light setCel: (+ (light cel?) 1) forceUpd:)
				)
				(self ignoreActors: 1 setCycle: End self)
				(if openSnd (elevatorSound number: openSnd play:))
			)
			(4
				(self stopUpd:)
				(if exiting
					(if polyCode (self perform: polyCode))
					(= state 2)
					(ego
						setPri: -1
						setMotion: MoveTo (self approachX?) (self approachY?) self
					)
				else
					(= state 3)
					(ego setMotion: MoveTo moveToX moveToY self)
				)
			)
			(3
				(= state 2)
				(ego setHeading: 180 self)
			)
			(2
				(= state 1)
				(self setCycle: Beg self)
				(if closeSnd (elevatorSound number: closeSnd play:))
			)
			(1
				(= state 0)
				(self stopUpd: ignoreActors: 0)
				(if (and light (not exiting))
					(light setCel: (- (light cel?) 1) forceUpd:)
				)
				(self _approachVerbs: 26505)
				(= busy 0)
				(cond 
					(exiting
						(= exiting 0)
						(if (== curRoomNum 10)
							(self approachVerbs: 3 4)
						else
							(self approachVerbs: 3)
						)
						(if exitScript
							(curRoom setScript: exitScript)
						else
							(HandsOn)
						)
					)
					((IsObject whereTo) (whereTo exiting: 1 open:))
					(else (curRoom newRoom: whereTo))
				)
			)
			(0 0)
		)
	)
	
	(method (inFront &tmp temp0)
		(= temp0 0)
		(if
			(and
				(not busy)
				(< locked 2)
				(ego inRect: rectL rectT rectR rectB)
				(OneOf (ego loop?) 7 3 6)
				((ego cycler?) isKindOf: StopWalk)
				(ego mover?)
				(!= ((ego mover?) client?) CueObj)
				(!= (CueObj theInvItem?) 1)
			)
			(= temp0 (= busy 1))
		)
		(return temp0)
	)
	
	(method (open)
		(if (and locked (not exiting))
			(if (== locked 1)
				(= locked 2)
				(if lockStr (Printf 817 0 lockStr) else (Print 817 1))
			)
			(self startUpd:)
			(HandsOn)
		else
			(HandsOff)
			(= busy 1)
			(= state 6)
			(if exiting (ego posn: moveToX moveToY setPri: 0))
		)
	)
)

(instance elevatorSound of Sound
	(properties)
)
