;;; Sierra Script 1.0 - (do not remove this comment)
(script# 102)
(include game.sh)
(use Main)
(use CyclingProp)
(use LastLink)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	intro2Rm 0
)

(local
	introWater
	soundCued
)
(procedure (HideCredit)
	(creditTitle hide:)
	(creditTitle2 hide:)
	(dash dispose:)
	(views eachElementDo: #dispose release:)
)

(procedure (AddViews theView theLoop theCel theX theY)
	(views
		add:
			((View new:)
				view: theView
				loop: theLoop
				cel: theCel
				x: theX
				y: theY
				init:
				setPri: 12
				yourself:
			)
	)
)

(instance intro2Rm of Room
	
	(method (init)
		(super init:)
		(self curPic: 100)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(LoadMany VIEW 56 60 400 500 600 700 800 899)
		((= introWater (CyclingProp new:))
			view: 500
			loop: 0
			x: 114
			y: 122
			ignoreActors: TRUE
			init:
		)
		(theQueuedSound client: self)
		(self setScript: creditScript)
	)
	
	(method (dispose)
		(views dispose:)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(HandsOn)
		(theQueuedSound dispose:)
		(super dispose:)
	)
	
	(method (handleEvent)
		(self newRoom: 1)
	)
	
	(method (cue)
		(if (and script (not soundCued))
			((LastLink 118 self) cue:)
			(if (== (theQueuedSound signal?) -1)
				(= soundCued TRUE)
			)
		)
	)
)

(instance creditScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(theGame setCursor: theCursor 0)
				(self setScript: kenWilliamsScript self)
			)
			(2
				(HideCredit)
				(= cycles 2)
			)
			(3
				(self setScript: jimWallsScript self)
			)
			(4
				(HideCredit)
				(= cycles 2)
			)
			(5
				(self setScript: gurukaScript self)
			)
			(6
				(HideCredit)
				(= cycles 2)
			)
			(7
				(self setScript: programmerScript self)
			)
			(8
				(HideCredit)
				(= cycles 2)
			)
			(9
				(self setScript: artScript self)
			)
			(10
				(HideCredit)
				(= cycles 2)
			)
			(11
				(self setScript: musicScript self)
			)
			(12
				(HideCredit)
				(= cycles 2)
			)
			(13
				(self setScript: systemScript self)
			)
			(14
				(HideCredit)
				(= cycles 2)
			)
			(15
				(self setScript: thanksScript self)
			)
			(16
				(= cycles 2)
			)
			(17
				(curRoom newRoom: 1)
			)
		)
	)
)

(instance kenWilliamsScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(creditTitle loop: 7 cel: 0 x: 63 y: 27 init:)
				(creditTitle2 init:)
				(dash init: 147 55)
				(= seconds 1)
			)
			(1
				(AddViews 400 3 9 18 83)
				(AddViews 400 3 4 31 83)
				(AddViews 400 3 12 45 83)
				(AddViews 400 4 2 70 83)
				(AddViews 400 3 7 81 83)
				(AddViews 400 3 10 90 83)
				(AddViews 400 3 10 101 83)
				(AddViews 400 3 7 109 83)
				(AddViews 400 3 0 119 83)
				(AddViews 400 3 11 133 83)
				(AddViews 400 4 0 148 83)
				(if soundCued
					(= seconds 10)
				)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance jimWallsScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(creditTitle loop: 2 cel: 0 x: 83 y: 39 show:)
				(dash init: 156 45)
				(= seconds 1)
			)
			(1
				(AddViews 400 3 8 18 75)
				(AddViews 400 3 7 27 75)
				(AddViews 400 3 11 38 75)
				(AddViews 400 4 2 64 75)
				(AddViews 400 3 0 78 75)
				(AddViews 400 3 10 90 75)
				(AddViews 400 3 10 101 75)
				(AddViews 400 4 0 113 75)
				(if soundCued
					(= seconds 10)
				)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance gurukaScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(creditTitle2 loop: 6 cel: 0 x: 75 y: 25 show:)
				(dash init: 150 31)
				(= seconds 1)
			)
			(1
				(AddViews 400 3 5 18 62)
				(AddViews 400 4 5 31 62)
				(AddViews 400 3 15 44 62)
				(AddViews 400 4 5 58 62)
				(AddViews 400 3 9 71 62)
				(AddViews 400 3 0 83 62)
				(AddViews 400 4 0 102 62)
				(AddViews 400 3 7 110 62)
				(AddViews 400 3 12 121 62)
				(AddViews 400 3 5 134 62)
				(AddViews 400 3 6 146 62)
				(AddViews 400 3 9 18 80)
				(AddViews 400 3 6 29 80)
				(AddViews 400 3 0 43 80)
				(AddViews 400 3 10 55 80)
				(AddViews 400 4 0 66 80)
				(AddViews 400 3 0 78 80)
				(if soundCued
					(= seconds 10)
				)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance programmerScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(creditTitle loop: 1 cel: 0 x: 101 y: 29 show:)
				(dash init: 168 35)
				(= seconds 1)
			)
			(1
				(self setScript: jmhScript self)
			)
			(2
				(self setScript: pabloScript self)
			)
			(3
				(self setScript: dougScript)
				(if soundCued
					(= seconds 10)
				)
			)
			(4
				(self dispose:)
			)
		)
	)
)

(instance jmhScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 8 18 65)
				(AddViews 400 4 4 27 65)
				(AddViews 400 3 11 44 65)
				(AddViews 400 3 0 60 65)
				(AddViews 400 3 15 73 65)
				(AddViews 400 3 9 87 65)
				(AddViews 400 3 6 105 65)
				(AddViews 400 3 13 119 65)
				(AddViews 400 3 13 132 65)
				(AddViews 400 3 3 144 65)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance pabloScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 14 18 86)
				(AddViews 400 3 0 31 86)
				(AddViews 400 3 1 43 86)
				(AddViews 400 3 10 56 86)
				(AddViews 400 3 13 68 86)
				(AddViews 400 3 5 90 86)
				(AddViews 400 3 6 102 86)
				(AddViews 400 3 4 116 86)
				(AddViews 400 3 12 130 86)
				(AddViews 400 3 7 139 86)
				(AddViews 400 4 0 148 86)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance dougScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 3 18 107)
				(AddViews 400 3 13 31 107)
				(AddViews 400 4 5 43 107)
				(AddViews 400 3 5 56 107)
				(AddViews 400 3 13 78 107)
				(AddViews 400 3 10 90 107)
				(AddViews 400 3 3 102 107)
				(AddViews 400 4 6 116 107)
				(AddViews 400 3 7 125 107)
				(AddViews 400 3 4 136 107)
				(AddViews 400 3 10 148 107)
				(AddViews 400 3 3 160 107)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance scottScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 10 18 86)
				(AddViews 400 3 0 31 86)
				(AddViews 400 3 15 44 86)
				(AddViews 400 3 15 58 86)
				(AddViews 400 4 3 72 86)
				(AddViews 400 4 0 96 86)
				(AddViews 400 3 2 109 86)
				(AddViews 400 3 13 120 86)
				(AddViews 400 4 1 133 86)
				(AddViews 400 4 1 146 86)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance artScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(creditTitle loop: 0 cel: 0 x: 37 y: 33 show:)
				(creditTitle2 loop: 0 cel: 1 x: 114 y: 30 show:)
				(dash init: 159 35)
				(= seconds 1)
			)
			(1
				(self setScript: cheriScript self)
			)
			(2
				(self setScript: jimLarsenScript)
				(if soundCued
					(= seconds 10)
				)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance cheriScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 2 18 62)
				(AddViews 400 3 6 30 62)
				(AddViews 400 3 4 44 62)
				(AddViews 400 3 15 57 62)
				(AddViews 400 4 3 69 62)
				(AddViews 400 3 10 80 62)
				(AddViews 400 3 10 100 62)
				(AddViews 400 3 13 112 62)
				(AddViews 400 4 3 124 62)
				(AddViews 400 3 3 135 62)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance jimLarsenScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 8 18 88)
				(AddViews 400 3 7 27 88)
				(AddViews 400 3 11 38 88)
				(AddViews 400 3 10 63 88)
				(AddViews 400 3 0 74 88)
				(AddViews 400 3 15 87 88)
				(AddViews 400 4 0 100 88)
				(AddViews 400 3 4 112 88)
				(AddViews 400 3 12 126 88)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance musicScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(creditTitle loop: 8 cel: 0 x: 65 y: 32 show:)
				(dash init: 143 36)
				(= seconds 1)
			)
			(1
				(AddViews 400 3 11 18 64)
				(AddViews 400 3 0 34 64)
				(AddViews 400 3 15 47 64)
				(AddViews 400 3 6 60 64)
				(AddViews 400 4 0 80 64)
				(AddViews 400 3 4 94 64)
				(AddViews 400 3 7 103 64)
				(AddViews 400 3 1 113 64)
				(AddViews 400 3 4 127 64)
				(AddViews 400 3 15 140 64)
				(AddViews 400 4 1 152 64)
				(if soundCued
					(= seconds 10)
				)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance systemScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(creditTitle loop: 9 cel: 0 x: 34 y: 26 show:)
				(creditTitle2 loop: 9 cel: 1 x: 124 y: 25 show:)
				(= seconds 1)
			)
			(1
				(self setScript: jeffScript self)
			)
			(2
				(self setScript: bobScript self)
			)
			(3
				(self setScript: systemPabloScript self)
			)
			(4
				(self setScript: stuScript)
				(if soundCued
					(= seconds 10)
				)
			)
			(5
				(self dispose:)
			)
		)
	)
)

(instance jeffScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 8 8 43)
				(AddViews 400 3 4 21 43)
				(AddViews 400 4 6 34 43)
				(AddViews 400 4 6 47 43)
				(AddViews 400 4 0 64 43)
				(AddViews 400 4 1 75 43)
				(AddViews 400 3 4 88 43)
				(AddViews 400 3 14 101 43)
				(AddViews 400 3 6 112 43)
				(AddViews 400 3 4 125 43)
				(AddViews 400 3 12 138 43)
				(AddViews 400 4 0 149 43)
				(AddViews 400 3 13 160 43)
				(AddViews 400 3 12 172 43)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance bobScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 1 8 62)
				(AddViews 400 3 13 21 62)
				(AddViews 400 3 1 33 62)
				(AddViews 400 3 6 52 62)
				(AddViews 400 3 4 65 62)
				(AddViews 400 3 7 73 62)
				(AddViews 400 4 1 82 62)
				(AddViews 400 3 11 96 62)
				(AddViews 400 3 0 111 62)
				(AddViews 400 3 12 124 62)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance systemPabloScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 14 8 80)
				(AddViews 400 3 0 20 80)
				(AddViews 400 3 1 32 80)
				(AddViews 400 3 10 44 80)
				(AddViews 400 3 13 55 80)
				(AddViews 400 3 5 74 80)
				(AddViews 400 3 6 85 80)
				(AddViews 400 3 4 98 80)
				(AddViews 400 3 12 111 80)
				(AddViews 400 3 7 119 80)
				(AddViews 400 4 0 127 80)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance stuScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 4 0 6 99)
				(AddViews 400 4 1 17 99)
				(AddViews 400 4 5 30 99)
				(AddViews 400 3 0 42 99)
				(AddViews 400 3 15 54 99)
				(AddViews 400 4 1 66 99)
				(AddViews 400 3 5 81 99)
				(AddViews 400 3 13 93 99)
				(AddViews 400 3 10 104 99)
				(AddViews 400 3 3 114 99)
				(AddViews 400 4 0 125 99)
				(AddViews 400 4 1 136 99)
				(AddViews 400 3 4 149 99)
				(AddViews 400 3 7 157 99)
				(AddViews 400 3 12 167 99)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance thanksScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(creditTitle loop: 7 cel: 1 x: 79 y: 27 show:)
				(dash init: 149 29)
				(= seconds 1)
			)
			(1
				(self setScript: bobStewartScript self)
			)
			(2
				(usNavy init:)
				(if soundCued
					(= seconds 10)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance bobStewartScript of Script
		
	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 1 14 61)
				(AddViews 400 3 13 28 61)
				(AddViews 400 3 1 41 61)
				(AddViews 400 4 0 59 61)
				(AddViews 400 4 1 70 61)
				(AddViews 400 3 4 83 61)
				(AddViews 400 4 2 97 61)
				(AddViews 400 3 0 111 61)
				(AddViews 400 3 15 123 61)
				(AddViews 400 4 1 135 61)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance jimTracyScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(AddViews 400 3 8 14 64)
				(AddViews 400 3 7 23 64)
				(AddViews 400 3 11 34 64)
				(AddViews 400 4 1 54 64)
				(AddViews 400 3 15 67 64)
				(AddViews 400 3 0 80 64)
				(AddViews 400 3 2 92 64)
				(AddViews 400 4 3 104 64)
				(= cycles 2)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance usNavy of View
	(properties
		y 83
		x 9
		view 500
		loop 3
	)
)

(instance dash of Prop
	(properties
		y 45
		x 156
		view 400
		loop 5
	)
	
	(method (init toX toY)
		(super init:)
		(if (== howFast fast)
			(self posn: toX toY cel: 0 setCycle: BegLoop self)
		else
			(self posn: toX toY setCel: 0)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance creditTitle of View
	(properties
		y 39
		x 83
		view 400
		loop 2
	)
)

(instance creditTitle2 of View
	(properties
		y 49
		x 79
		view 400
		loop 6
	)
)

(instance views of Collection)
