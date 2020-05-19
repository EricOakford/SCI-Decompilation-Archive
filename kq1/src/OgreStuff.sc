;;; Sierra Script 1.0 - (do not remove this comment)
(script# OGRE)
(include game.sh)
(use Main)
(use Intrface)
(use Chase)
(use Timer)
(use Avoider)
(use Motion)
(use System)

(public
	OgreStuff 0
)

(local
	ogreConfused
	ogreSensedInvisibleEgo
)
(instance OgreStuff of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(Print 803 0)
	)
	
	(method (doit &tmp invis)
		(super doit:)
		(= invis (Btst fInvisible))
		(cond 
			((and (not ogreConfused) invis)
				(= ogreConfused TRUE)
				(Print 803 6)
				(theMenace setMotion: 0)
			)
			((and ogreConfused (not ogreSensedInvisibleEgo) (< (ego distanceTo: theMenace) 12))
				(= ogreSensedInvisibleEgo TRUE)
				(Print 803 7)
				(self changeState: 2)
			)
			((and ogreConfused (not invis))
				(= ogreConfused FALSE)
				(Print 803 8)
				(self changeState: 1)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= ((ScriptID 0 21) signal?) -1)
					((ScriptID 0 21) loop: 1 fade:)
				)
				((ScriptID 0 23) number: 35 loop: -1 play:)
				(theMenace
					view: 130
					setAvoider: Avoider
					illegalBits: -28672
					setCycle: Walk
					cycleSpeed: 0
					init:
				)
				(switch prevRoomNum
					((curRoom west?)
						(theMenace posn: 320 155 setMotion: MoveTo 314 155 self)
					)
					((curRoom east?)
						(theMenace posn: -10 146 setMotion: MoveTo 10 146 self)
					)
					(else 
						(if (Random 0 1)
							(theMenace posn: 320 160 setMotion: MoveTo 314 155 self)
						else
							(theMenace posn: -10 146 setMotion: MoveTo 12 146 self)
						)
					)
				)
			)
			(1
				(theMenace setStep: 5 3 setMotion: Chase ego 20 self)
			)
			(2
				(if (and (not (Btst fClimbingHill)) (== newRoomNum curRoomNum))
					(HandsOff)
					(theMenace hide:)
					(ego view: 20 setMotion: 0 loop: 0 cel: 0 setCycle: Forward)
					(if (== (curRoom script?) (ScriptID 782 0))
						((ScriptID 782 1) stop:)
					)
					((ScriptID 0 21) number: 46 loop: 1 init: play: self)
				else
					(self changeState: 5)
				)
			)
			(3
				(ego loop: 1 cel: 0 setCycle: EndLoop)
				(theMenace
					posn: (ego x?) (ego y?)
					loop: 4
					show:
					stopUpd:
				)
				(Timer set: self 3)
			)
			(4
				((ScriptID 0 23) fade:)
				(EgoDead {You were warned ogre and ogre again...})
			)
			(5
				(theMenace setMotion: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((or (Said 'look,check/ogre,man[<green]') (MousedOn theMenace event shiftDown))
				(Print 803 1)
				(event claimed: TRUE)
			)
			((Said 'talk,speak[/ogre]')
				(Print 803 2)
			)
			((Said 'attack,kill/ogre')
				(Print 803 3)
			)
			((Said 'use,throw/dagger')
				(if (not (ego has: iDagger))
					(Print 803 4)
				else
					(Print 803 5)
				)
			)
		)
	)
)
