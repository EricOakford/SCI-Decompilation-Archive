;;; Sierra Script 1.0 - (do not remove this comment)
(script# SORCERER)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Chase)
(use Avoider)
(use Motion)
(use System)

(public
	SorcStuff 0
)

(instance SorcStuff of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(Print 800 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 23) number: 34 loop: -1 play:)
				(theMenace
					view: 100
					setAvoider: Avoider
					setCycle: Walk
					setCel: -1
					illegalBits: -28672
					setStep: 4 3
					init:
				)
				(switch prevRoomNum
					((curRoom west?)
						(theMenace posn: 330 158 setMotion: MoveTo 314 155 self)
					)
					((curRoom east?)
						(theMenace posn: -10 147 setMotion: MoveTo 6 143 self)
					)
					(else 
						(if (Random 0 1)
							(theMenace posn: 330 158 setMotion: MoveTo 314 155 self)
						else
							(theMenace posn: -10 147 setMotion: MoveTo 46 143 self)
						)
					)
				)
			)
			(1
				(theMenace setMotion: Chase ego 30 self)
			)
			(2
				(if
				(and (not (Btst fClimbingHill)) (== newRoomNum curRoomNum))
					(HandsOff)
					(if (== (ego view?) (if (Btst fLittleEgo) 23 else 16))
						(SetMenu duckI p_value FALSE p_text { Duck} p_text 'duck')
					)
					(Face theMenace ego)
					((ScriptID 0 21) number: 26 loop: 1 play:)
					(theMenace
						setMotion: 0
						view: 101
						cel: 0
						cycleSpeed: 1
						setCycle: EndLoop self
					)
					(if (Btst fInvisible)
						(Print 800 4)
					)
				else
					(self dispose:)
				)
			)
			(3
				(ego
					cycleSpeed: 1
					view: 28
					cel: 0
					loop: 0
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(4
				(Print 800 5)
				((ScriptID 0 21) number: 90 loop: -1 play:)
				(theMenace view: 102 cel: 11 setCycle: BegLoop)
				(ego loop: 1 cel: 0 cycleSpeed: 2 setCycle: EndLoop)
				(if (Btst fInvisible)
					(Print 800 6)
					(Bclr fInvisible)
				)
				(if menaceInRoom
					(ego setScript: (ScriptID 602 2))
					(if (== menaceInRoom OGRE)
						(self dispose:)
					)
				else
					(= seconds 12)
				)
			)
			(5
				((ScriptID 0 23) stop:)
				(ego loop: 2 cel: 0 cycleSpeed: 40 setCycle: EndLoop self)
				(theMenace setCycle: 0 hide:)
			)
			(6
				((ScriptID 0 21) loop: 1 fade:)
				(NormalEgo)
				(ego loop: loopS)
				(= cycles 5)
			)
			(7
				(Print 800 7)
				(HandsOn)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((or (Said 'look,check/sorcerer,man') (MousedOn theMenace event shiftDown))
				(Print 800 1)
				(event claimed: TRUE)
			)
			((Said 'talk,speak[/sorcerer]')
				(Print 800 2)
			)
			((Said 'kill/sorcerer,man')
				(Print 800 3)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)
