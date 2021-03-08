;;; Sierra Script 1.0 - (do not remove this comment)
(script# 137)
(include game.sh) (include "137.shm")
(use Main)
(use Feature)
(use LoadMany)
(use Game)
(use Actor)
(use System)

(public
	rm137 0
)

(local
	answer1Y
	answer2Y
	answer3Y
	answer4Y
	answer5Y
	[local5 10] = [84 76 76 84 92 84 100 76 68 84]
	[local15 10] = [97 97 105 105 105 97 113 89 105 105]
	[local25 10] = [110 118 118 118 126 118 134 118 126 118]
	[local35 10] = [123 139 131 139 147 139 147 131 147 139]
	[local45 10] = [136 152 144 152 168 160 160 146 176 152]
	[local55 20] = [3 4 4 2 4 2 4 3 0 0 2 3 0 4 1 4 1 1 2 4]
	[str 200]
)

(define QUESTION_WIDTH 240)
(define ANSWER_WIDTH 235)

(class AnswerBox of View
	(properties
		view 128
		loop 1
		cel 1
		priority 15
		boxnum 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(DrawCel 128 1 0 x y)
				(if (== [local55 curTestQuestion] boxnum) (++ testScore))
				(theMusic2 number: 124 setLoop: 1 play:)
				(sTest setScript: sNextQuest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rm137 of Room
	(properties
		picture 24
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(self setRegions: rgStarCon)
		(LoadMany RES_VIEW 128)
		(ego view: 1)
		(exitTest init: setOnMeCheck: ftrControl cGREEN)
		(super init:)
		(theGame handsOn:)
		(theIconBar disable: ICON_WALK ICON_TALK ICON_ORDER)
		(curRoom setScript: sTest)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
				(and
					(InRect 20 0 300 200 mouseX mouseY)
					(!= (theIconBar curIcon?) (theIconBar at: ICON_DO))
				)
				(theIconBar select: (theIconBar at: ICON_DO))
				(theGame setCursor: 982)
			)
			(
				(and
					(not (InRect 20 0 300 200 mouseX mouseY))
					(!= (theIconBar curIcon?) (theIconBar at: ICON_LOOK))
				)
				(theIconBar select: (theIconBar at: ICON_DO))
				(theGame setCursor: 981)
			)
		)
	)
)

(instance sTest of Script
	
	(method (doit)
		(if (GameIsRestarting)
			(self changeState: 0)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= answer1Y [local5 curTestQuestion])
				(= answer2Y [local15 curTestQuestion])
				(= answer3Y [local25 curTestQuestion])
				(= answer4Y [local35 curTestQuestion])
				(= answer5Y [local45 curTestQuestion])
				(box1 init:)
				(= state curTestQuestion)
				(= cycles 1)
			)
			(1
				(Message MsgGet 137 N_QUESTION1 NULL NULL 1 @str)
				(Display @str
					p_at 38 55
					p_color 39
					p_back 1
					p_font 1605
					p_width QUESTION_WIDTH
				)
				(Message MsgGet 137 N_QUESTION1 NULL NULL 2 @str)
				(Display @str
					p_at 55 answer1Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION1 NULL NULL 3 @str)
				(Display @str
					p_at 55 answer2Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION1 NULL NULL 4 @str)
				(Display @str
					p_at 55 answer3Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION1 NULL NULL 5 @str)
				(Display @str
					p_at 55 answer4Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION1 NULL NULL 6 @str)
				(Display @str
					p_at 55 answer5Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
			)
			(2
				(Message MsgGet 137 N_QUESTION2 NULL NULL 1 @str)
				(Display @str
					p_at 38 55
					p_color 39
					p_back 1
					p_font 1605
					p_width QUESTION_WIDTH
				)
				(Message MsgGet 137 N_QUESTION2 NULL NULL 2 @str)
				(Display @str
					p_at 55 answer1Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION2 NULL NULL 3 @str)
				(Display @str
					p_at 55 answer2Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION2 NULL NULL 4 @str)
				(Display @str
					p_at 55 answer3Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION2 NULL NULL 5 @str)
				(Display @str
					p_at 55 answer4Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION2 NULL NULL 6 @str)
				(Display @str
					p_at 55 answer5Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
			)
			(3
				(Message MsgGet 137 N_QUESTION3 NULL NULL 1 @str)
				(Display @str
					p_at 38 55
					p_color 39
					p_back 1
					p_font 1605
					p_width QUESTION_WIDTH
				)
				(Message MsgGet 137 N_QUESTION3 NULL NULL 2 @str)
				(Display @str
					p_at 55 answer1Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION3 NULL NULL 3 @str)
				(Display @str
					p_at 55 answer2Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION3 NULL NULL 4 @str)
				(Display @str
					p_at 55 answer3Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION3 NULL NULL 5 @str)
				(Display @str
					p_at 55 answer4Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION3 NULL NULL 6 @str)
				(Display @str
					p_at 55 answer5Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
			)
			(4
				(Message MsgGet 137 N_QUESTION4 NULL NULL 1 @str)
				(Display @str
					p_at 38 55
					p_color 39
					p_back 1
					p_font 1605
					p_width QUESTION_WIDTH
				)
				(Message MsgGet 137 N_QUESTION4 NULL NULL 2 @str)
				(Display @str
					p_at 55 answer1Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION4 NULL NULL 3 @str)
				(Display @str
					p_at 55 answer2Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION4 NULL NULL 4 @str)
				(Display @str
					p_at 55 answer3Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION4 NULL NULL 5 @str)
				(Display @str
					p_at 55 answer4Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION4 NULL NULL 6 @str)
				(Display @str
					p_at 55 answer5Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
			)
			(5
				(Message MsgGet 137 N_QUESTION5 NULL NULL 1 @str)
				(Display @str
					p_at 38 55
					p_color 39
					p_back 1
					p_font 1605
					p_width QUESTION_WIDTH
				)
				(Message MsgGet 137 N_QUESTION5 NULL NULL 2 @str)
				(Display @str
					p_at 55 answer1Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION5 NULL NULL 3 @str)
				(Display @str
					p_at 55 answer2Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION5 NULL NULL 4 @str)
				(Display @str
					p_at 55 answer3Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION5 NULL NULL 5 @str)
				(Display @str
					p_at 55 answer4Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION5 NULL NULL 6 @str)
				(Display @str
					p_at 55 answer5Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
			)
			(6
				(Message MsgGet 137 N_QUESTION6 NULL NULL 1 @str)
				(Display @str
					p_at 38 55
					p_color 39
					p_back 1
					p_font 1605
					p_width QUESTION_WIDTH
				)
				(Message MsgGet 137 N_QUESTION6 NULL NULL 2 @str)
				(Display @str
					p_at 55 answer1Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION6 NULL NULL 3 @str)
				(Display @str
					p_at 55 answer2Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION6 NULL NULL 4 @str)
				(Display @str
					p_at 55 answer3Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION6 NULL NULL 5 @str)
				(Display @str
					p_at 55 answer4Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION6 NULL NULL 6 @str)
				(Display @str
					p_at 55 answer5Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
			)
			(7
				(Message MsgGet 137 N_QUESTION7 NULL NULL 1 @str)
				(Display @str
					p_at 38 55
					p_color 39
					p_back 1
					p_font 1605
					p_width QUESTION_WIDTH
				)
				(Message MsgGet 137 N_QUESTION7 NULL NULL 2 @str)
				(Display @str
					p_at 55 answer1Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION7 NULL NULL 3 @str)
				(Display @str
					p_at 55 answer2Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION7 NULL NULL 4 @str)
				(Display @str
					p_at 55 answer3Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION7 NULL NULL 5 @str)
				(Display @str
					p_at 55 answer4Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION7 NULL NULL 6 @str)
				(Display @str
					p_at 55 answer5Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
			)
			(8
				(Message MsgGet 137 N_QUESTION8 NULL NULL 1 @str)
				(Display @str
					p_at 38 55
					p_color 39
					p_back 1
					p_font 1605
					p_width QUESTION_WIDTH
				)
				(Message MsgGet 137 N_QUESTION8 NULL NULL 2 @str)
				(Display @str
					p_at 55 answer1Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION8 NULL NULL 3 @str)
				(Display @str
					p_at 55 answer2Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION8 NULL NULL 4 @str)
				(Display @str
					p_at 55 answer3Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION8 NULL NULL 5 @str)
				(Display @str
					p_at 55 answer4Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
			)
			(9
				(Message MsgGet 137 N_QUESTION9 NULL NULL 1 @str)
				(Display @str
					p_at 38 55
					p_color 39
					p_back 1
					p_font 1605
					p_width QUESTION_WIDTH
				)
				(Message MsgGet 137 N_QUESTION9 NULL NULL 2 @str)
				(Display @str
					p_at 55 answer1Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION9 NULL NULL 3 @str)
				(Display @str
					p_at 55 answer2Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION9 NULL NULL 4 @str)
				(Display @str
					p_at 55 answer3Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION9 NULL NULL 5 @str)
				(Display @str
					p_at 55 answer4Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
			)
			(10
				(Message MsgGet 137 N_QUESTION10 NULL NULL 1 @str)
				(Display @str
					p_at 38 55
					p_color 39
					p_back 1
					p_font 1605
					p_width QUESTION_WIDTH
				)
				(Message MsgGet 137 N_QUESTION10 NULL NULL 2 @str)
				(Display @str
					p_at 55 answer1Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION10 NULL NULL 3 @str)
				(Display @str
					p_at 55 answer2Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION10 NULL NULL 4 @str)
				(Display @str
					p_at 55 answer3Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION10 NULL NULL 5 @str)
				(Display @str
					p_at 55 answer4Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
				(Message MsgGet 137 N_QUESTION10 NULL NULL 6 @str)
				(Display @str
					p_at 55 answer5Y
					p_color 36
					p_back 1
					p_font 1605
					p_width ANSWER_WIDTH
				)
			)
			(11
				(theMusic2 number: 125 setLoop: 1 play: self)
			)
			(12
				(curRoom newRoom: 135)
				(self dispose:)
			)
		)
	)
)

(instance sNextQuest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(++ curTestQuestion)
				(= ticks 45)
			)
			(1
				(box1 dispose:)
				(DrawPic 24 PIXELDISSOLVE)
				(= cycles 1)
			)
			(2
				(if (< curTestQuestion 10)
					(= answer1Y [local5 curTestQuestion])
					(= answer2Y [local15 curTestQuestion])
					(= answer3Y [local25 curTestQuestion])
					(= answer4Y [local35 curTestQuestion])
					(= answer5Y [local45 curTestQuestion])
					(box1 init:)
					(= cycles 15)
				else
					(= cycles 1)
				)
			)
			(3
				(sTest cue:)
				(theGame handsOn:)
				(theIconBar disable: ICON_WALK ICON_TALK ICON_ORDER)
				(self dispose:)
			)
		)
	)
)

(instance box1 of AnswerBox
	(properties
		boxnum 0
	)
	
	(method (init)
		(self cel: 1 posn: 37 (- answer1Y 1))
		(super init:)
		(self stopUpd:)
		(self ignoreActors: 1)
		(box2
			cel: 1
			posn: 37 (- answer2Y 1)
			init:
			ignoreActors: 1
			stopUpd:
		)
		(box3
			cel: 1
			posn: 37 (- answer3Y 1)
			init:
			ignoreActors: 1
			stopUpd:
		)
		(box4
			cel: 1
			posn: 37 (- answer4Y 1)
			init:
			ignoreActors: 1
			stopUpd:
		)
		(if (not (if (< 6 curTestQuestion) (< curTestQuestion 9)))
			(box5
				cel: 1
				posn: 37 (- answer5Y 1)
				init:
				ignoreActors: 1
				stopUpd:
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(box2 dispose:)
		(box3 dispose:)
		(box4 dispose:)
		(box5 dispose:)
	)
)

(instance box2 of AnswerBox
	(properties
		boxnum 1
	)
)

(instance box3 of AnswerBox
	(properties
		boxnum 2
	)
)

(instance box4 of AnswerBox
	(properties
		boxnum 3
	)
)

(instance box5 of AnswerBox
	(properties
		boxnum 4
	)
)

(instance exitTest of Feature
	(properties
		x 4
		y 20
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(curRoom newRoom: 135)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
