;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include game.sh)
(use Main)
(use PMouse)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm140 0
)

(local
	[local0 2] = [71]
	[heroX 6] = [29 101 202 18 212 201]
	[heroY 7] = [27 27 27 57 64 57]
)
(procedure (ShowPlate charClass)
	;NOTE: This procedure did not decompile correctly, but I've fixed that.
	(fightChar setCycle: 0 cel: 0 stopUpd:)
	(mageChar setCycle: 0 cel: 0 stopUpd:)
	(thiefChar setCycle: 0 cel: 0 stopUpd:) ;this was placed in with theTitle by mistake
	(theTitle
		setLoop: 1
		cel: charClass
		x: [heroX charClass]
		y: [heroY charClass]
		show:
	)
)

(instance rm140 of Room
	(properties
		picture 140
		style FADEOUT
	)
	
	(method (init)
		(soundFx client: 0)
		(super init: &rest)
		(LoadMany PICTURE 140 145)
		(Load VIEW 145 142)
		(super init:)
		(theGame setCursor: INVIS_CURSOR TRUE)
		(= useSortedFeatures FALSE)
		(= pMouse 0)
		(theTitle init: hide:)
		(fightChar init:)
		(mageChar init:)
		(thiefChar init:)
		(roundRobin start: 0)
		(self setScript: roundRobin)
	)
	
	(method (dispose)
		(= useSortedFeatures TRUE)
		(= pMouse PseudoMouse)
		(super dispose:)
	)
)

(instance fightChar of Prop
	(properties
		x 78
		y 141
		view 140
		loop 1
		signal ignrAct
	)
)

(instance mageChar of Prop
	(properties
		x 158
		y 139
		view 140
		signal ignrAct
	)
)

(instance thiefChar of Prop
	(properties
		x 238
		y 140
		view 140
		loop 2
		signal ignrAct
	)
)

(instance theTitle of View
	(properties
		x 17
		y 208
		view 143
		signal ignrAct
	)
)

(instance roundRobin of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(Display {THE QUEST FOR GLORY}
					p_font 2510
					p_at 71 154
					p_color 25
				)
				(Display {ADVENTURE CONTINUES}
					p_font 2510
					p_at 61 168
					p_color 25
				)
				(Display {THE QUEST FOR GLORY}
					p_font 2510
					p_at 70 153
					p_color 30
				)
				(Display {ADVENTURE CONTINUES}
					p_font 2510
					p_at 60 167
					p_color 30
				)
				(theTitle loop: 0)
				(= seconds 2)
			)
			(2
				(ShowPlate FIGHTER)
				(fightChar setCycle: EndLoop self)
			)
			(3 (= seconds 3))
			(4
				(fightChar setCycle: BegLoop self)
			)
			(5
				(ShowPlate MAGIC_USER)
				(mageChar setCycle: CycleTo 4 1 self)
			)
			(6
				(fireBall
					loop: 0
					x: 158
					y: 139
					init:
					setScale: 139
					setCycle: EndLoop self
				)
				(music number: 12 play:)
				(mageChar setCycle: EndLoop)
			)
			(7
				(fireBall
					loop: 2
					y: 70
					setCycle: Forward
					cycleSpeed: 0
					setStep: 1 8
					setScale:
					scaleX: 10
					scaleY: 10
					maxScale: 360
				)
				(music number: 13 play:)
				(= cycles 2)
			)
			(8
				(= temp1 5)
				(= temp0 10)
				(while (< temp0 280)
					(fireBall scaleX: temp0 scaleY: temp0)
					(Animate (cast elements?) TRUE)
					(if (> temp0 90) (= temp1 18))
					(if (> temp0 200) (= temp1 26))
					(= temp0 (+ temp0 temp1))
				)
				(= cycles 2)
			)
			(9
				(fireBall dispose:)
				(= seconds 2)
			)
			(10
				(mageChar setCycle: BegLoop self)
			)
			(11
				(ShowPlate THIEF)
				(thiefChar setCycle: CycleTo 5 1 self)
			)
			(12
				(gem init: setPri: 14 setCycle: EndLoop self)
				(thiefChar setCycle: EndLoop)
				(music number: 15 play:)
			)
			(13 (= seconds 3))
			(14
				(gem dispose:)
				(thiefChar setCycle: BegLoop self)
			)
			(15
				(thiefChar setCycle: 0 cel: 0 stopUpd:)
				(theTitle hide:)
				(soundFx fade:)
				(= seconds 2)
			)
			(16 (curRoom newRoom: 120))
			(17
				(mouseDownHandler
					delete: fightChar mageChar thiefChar theTitle self
				)
				(keyDownHandler
					delete: self fightChar mageChar thiefChar theTitle
				)
				(switch (theTitle cel?)
					(0 (fightChar addToPic:))
					(1 (mageChar addToPic:))
					(2 (thiefChar addToPic:))
				)
				(theGame setCursor: theCursor FALSE)
				(= cycles 5)
			)
			(18
				(curRoom drawPic: (curRoom picture?) 9)
				(= seconds 5)
			)
			(19
				(directionHandler delete: self)
				(cast eachElementDo: #dispose)
				(Animate 0)
			)
		)
	)
)

(instance fireBall of Actor
	(properties
		y 139
		yStep 35
		view 142
		priority 14
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance gem of Prop
	(properties
		x 229
		y 141
		view 140
		loop 3
		priority 14
		signal fixPriOn
	)
)
