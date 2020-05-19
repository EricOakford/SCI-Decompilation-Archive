;;; Sierra Script 1.0 - (do not remove this comment)
(script# GARDEN)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use System)

(public
	gardenReg 0
)

(instance gardenReg of Region
	(properties)
	
	(method (init)
		(Load VIEW 1)
		(super init:)
		(Bclr fLookedAtGarden)
		(Bclr fLookedAtGardenBorder)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (& (ego onControl: origin) (| cBLUE cGREEN))
			(ego z: -5)
		else
			(ego z: 0)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event))
			(
				(and
					(& (event modifiers?) shiftDown)
					(or (Btst fLookedAtGarden) (Btst fLookedAtGardenBorder))
				)
				(Bclr fLookedAtGardenBorder)
				(Bclr fLookedAtGarden)
				(event claimed: TRUE)
			)
			(
				(or
					(Said 'look,check/garden,grass,bury,bush')
					(and
						(& (| cLGREEN cGREEN) (OnControl CMAP (event x?) (event y?)))
						(& (event modifiers?) shiftDown)
						(not (Btst fLookedAtGarden))
					)
				)
				(Bset fLookedAtGarden)
				(Print 604 0)
				(event claimed: TRUE)
			)
			(
				(or
					(Said 'look,check/brick,border')
					(and
						(& cYELLOW (OnControl CMAP (event x?) (event y?)))
						(& (event modifiers?) shiftDown)
						(not (Btst fLookedAtGardenBorder))
					)
				)
				(Bset fLookedAtGardenBorder)
				(Print 604 1)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room]')
						(Print 604 2)
					)
					((Said '<down')
						(if (& (ego onControl: origin) (| cBLUE cGREEN))
							(Print 604 3)
						else
							(event claimed: FALSE)
						)
					)
					((Said '/carrot')
						(cond 
							((ego has: iCarrot)
								(event claimed: FALSE)
							)
							((& (ego onControl: origin) (| cBLUE cGREEN))
								(Print 604 3)
							)
							(else
								(Print 604 4)
							)
						)
					)
				)
			)
			((Said 'get,take,pick/carrot')
				(cond 
					((ego has: iCarrot)
						(Print 604 5)
					)
					((curRoom script?)
						(Print 604 6)
					)
					((Btst fInvisible)
						(Print 604 7)
					)
					((& (ego onControl: origin) (| cBLUE cGREEN))
						(curRoom setScript: pickCarrot)
					)
					(else
						(Print 604 8)
					)
				)
			)
		)
	)
)

(instance pickCarrot of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and (ego isStopped:) (== state 0) (ego isBlocked:))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 134 135)
			)
			(1
				(ego
					view: 1
					setLoop:
						(if
							(and
								(< 120 (ego heading?))
								(< (ego heading?) 260)
								(< (ego loop?) 2)
							)
							2
						else
							-1
						)
					setMotion: 0
					setCycle: EndLoop self
				)
				(Bclr 10)
			)
			(2
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(Print 604 9)
				(ego get: iCarrot)
				(if (not (Btst fTrollDead)) (SolvePuzzle fPickedCarrot 2))
				(= cycles 4)
			)
			(3
				(ego setCycle: BegLoop self)
			)
			(4
				(NormalEgo)
				(ego illegalBits: -31744)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
