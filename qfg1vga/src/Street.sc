;;; Sierra Script 1.0 - (do not remove this comment)
(script# STREET) ;811
(include game.sh) (include "811.shm") (include "815.shm")
(use Main)
(use Procs)
(use PolyPath)
(use Game)
(use System)

(public
	Street 0
)

(local
	timesPickedLock
	timesClimbedWall
)
(instance Street of Region
	(method (init)
		(if
			(and
				(not Night)
				(or
					(!= (cSound number?) 93)
					(== (cSound prevSignal?) -1)
				)
			)
			(cSound priority: 0 number: 93 loop: -1 play:)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp thisControl)
		(cond 
			((== theVerb V_SLEEP)
				(cond 
					((and (< 700 Clock) (< Clock 2550))
						(messager say: N_STREET V_SLEEP C_DAY 1 0 STREET)
					)
					((!= (ego onControl: origin) cBLACK)
						(messager say: N_STREET V_SLEEP C_CANT_SLEEP_THERE 1 0 STREET)
					)
					((== curRoomNum 334)
						(ego setScript: (ScriptID SLEEPALLNIGHT 0) self)
					)
					(else
						(ego setScript: egoSleepsInStreet)
					)
				)
			)
			((or (== theVerb V_LOCKPICK) (== theVerb V_THIEFKIT))
				(= thisControl (ego onControl: origin))
				(cond 
					((not Night)
						(messager say: N_STREET V_LOCKPICK C_DAY 1 0 STREET)
					)
					((not [egoStats PICK])
						(messager say: N_STREET V_LOCKPICK C_NO_PICK 1 0 STREET)
					)
					((== thisControl cYELLOW)
						(curRoom notify: 1)
					)
					((or (== thisControl cLRED) (== thisControl cLGREEN) (== thisControl cLMAGENTA))
						(if (<= (Random 3 10) (++ timesPickedLock))
							(EgoDead C_DIE_BUSTED_PICK_LOCK C_DIE_BUSTED_PICK_LOCK_TITLE 1 0 503)
						else
							(TrySkill PICK 0 0)
							(messager say: N_STREET V_LOCKPICK C_DOOR_BARRED 1 0 STREET)
						)
					)
					(else
						(messager say: N_STREET V_LOCKPICK C_NO_DOORS 1 0 STREET)
					)
				)
			)
			(else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoSleepsInStreet of Script
	(method (changeState newState &tmp nextRoom)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_STREET V_SLEEP C_EGOSLEEPS 1 self STREET)
			)
			(1
				(theGame setCursor: waitCursor TRUE)
				(switch curRoomNum
					(320
						(ego setMotion: PolyPath 159 168 self)
					)
					(330
						(ego setMotion: PolyPath 159 168 self)
					)
					(else  (= ticks 20))
				)
			)
			(2
				(ego setScript: (ScriptID SLEEPALLNIGHT 0) self)
			)
			(3
				(= nextRoom curRoomNum)
				(= curRoomNum 999)
				(= ticks 90)
			)
			(4
				(curRoom newRoom: nextRoom)
			)
		)
	)
)
