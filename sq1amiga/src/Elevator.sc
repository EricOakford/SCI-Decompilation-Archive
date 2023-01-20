;;; Sierra Script 1.0 - (do not remove this comment)
(script# ELEVATOR) ;817
(include game.sh)
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
		cycleSpeed 8
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
		state 0
		pauseCounter 0
		moveToX 0
		moveToY 0
		rectT 0
		rectL 0
		rectB 0
		rectR 0
	)
	
	(method (init &tmp cWide)
		(super init: &rest)
		(if (== level currentFloor)
			(if (== curRoomNum 10)
				(self approachVerbs: verbDo verbUse)
			else
				(self approachVerbs: verbDo)
			)
		else
			(self _approachVerbs: ftrDefault)
		)
		(if light
			(light
				init:
				ignoreActors:
				setPri: 14
				posn: x (- y (+ (CelHigh view loop cel) 2))
			)
		)
		(= cWide (CelWide view loop cel))
		(= rectT y)
		(= rectL (- x (- (/ cWide 2) 3)))
		(= rectR (+ x (- (/ cWide 2) 3)))
		(= rectB (+ y 10))
		(= approachX x)
		(= approachY (+ y (/ (- rectB y) 2)))
		(self stopUpd: moveToX: x moveToY: (- y 7))
		(if exiting
			(= busy TRUE)
			(ego posn: moveToX moveToY setHeading: 180)
			(self open:)
		else
			(self ignoreActors: FALSE)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (== state 6)
			(if exiting
				(if (== (++ pauseCounter) (if (>= howFast medium) 40 else 20))
					(= pauseCounter 0)
					(self cue:)
				)
			)
			(if (not exiting)
				(self cue:)
			)
		)
	)
	
	(method (dispose)
		(if light
			(light dispose:)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (ego inRect: rectL rectT rectR rectB)
					(HandsOff)
					(self open:)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(verbUse
				(if locked
					(if (== theItem iKeyCard)
						(cond 
							(unlockScript
								(= locked FALSE)
								(= state 5)
								(curRoom setScript: unlockScript self)
							)
							(species
								(= locked FALSE)
								(HandsOff)
								(self open:)
							)
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
			(5
				(self open:)
			)
			(6
				(= state 4)
				(ego setMotion: 0)
				(if (and light (not exiting))
					(light setCel: (+ (light cel?) 1) forceUpd:)
				)
				(self ignoreActors: TRUE setCycle: EndLoop self)
				(if openSnd (elevatorSound number: openSnd play:))
			)
			(4
				(self stopUpd:)
				(if exiting
					(if polyCode
						(self perform: polyCode)
					)
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
				(self setCycle: BegLoop self)
				(if closeSnd (elevatorSound number: closeSnd play:))
			)
			(1
				(= state 0)
				(self stopUpd: ignoreActors: 0)
				(if (and light (not exiting))
					(light setCel: (- (light cel?) 1) forceUpd:)
				)
				(self _approachVerbs: ftrDefault)
				(= busy 0)
				(cond 
					(exiting
						(= exiting 0)
						(if (== curRoomNum 10)
							(self approachVerbs: verbDo verbUse)
						else
							(self approachVerbs: verbDo)
						)
						(if exitScript
							(curRoom setScript: exitScript)
						else
							(HandsOn)
						)
					)
					((IsObject whereTo)
						(whereTo exiting: TRUE open:)
					)
					(else
						(curRoom newRoom: whereTo)
					)
				)
			)
			(0 0)
		)
	)
	
	(method (inFront &tmp ret)
		(= ret 0)
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
			(= ret (= busy TRUE))
		)
		(return ret)
	)
	
	(method (open)
		(if (and locked (not exiting))
			(if (== locked 1)
				(= locked 2)
				(if lockStr
					(Printf 817 0 lockStr)
				else
					(Print 817 1)
				)
			)
			(self startUpd:)
			(HandsOn)
		else
			(HandsOff)
			(= busy TRUE)
			(= state 6)
			(if exiting
				(ego posn: moveToX moveToY setPri: 0)
			)
		)
	)
)

(instance elevatorSound of Sound)
